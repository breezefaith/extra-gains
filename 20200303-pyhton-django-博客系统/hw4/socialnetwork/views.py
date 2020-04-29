# coding=utf-8
import importlib,sys
importlib.reload(sys)

from django.shortcuts import render, redirect

# Create your views here.
from django.core import serializers
from django.http import HttpResponse, JsonResponse, Http404
from django.contrib.auth.models import User
from django.contrib.auth import authenticate
from django.db.models import Q
from django.views.decorators.csrf import csrf_exempt
from django.utils import timezone
from datetime import datetime
import dateutil.parser
import json
import time
from socialnetwork.models import Profile, Post, Comment
from socialnetwork.model_forms import ProfileForm


def index(request):
    if request.session.get('cur_user_id') == None:
        return redirect("/socialnetwork/login")
    return global_stream(request)


def login(request):
    if request.method == "POST":
        username = request.POST.get('username')
        password = request.POST.get('password')
        user = authenticate(username=username, password=password)

        if user is not None:
            request.session['cur_user_id'] = user.id
            return redirect("/socialnetwork")
        else:
            return render(request, 'login.html', {
                'message': 'Username or password is wrong',
                'username': username
            })
    return render(request, 'login.html')


def logout(request):
    request.session['cur_user_id'] = None
    return redirect("/socialnetwork/login")


def register(request):
    if request.method == "POST":
        username = request.POST.get('username')
        password = request.POST.get('password')
        confirm_password = request.POST.get('confirmPassword')
        email = request.POST.get('email')
        first_name = request.POST.get('firstName')
        last_name = request.POST.get('lastName')

        if password != confirm_password:
            return render(request, 'register.html', {
                'message': 'The passwords are different'
            })

        if len(User.objects.filter(username=username).values()) == 0:
            user = User.objects.create_user(
                username=username, email=email, password=password, first_name=first_name, last_name=last_name)
            user.save()

            profile = Profile(user=user)
            profile.save()

            request.session['cur_user_id'] = user.id
            return redirect("/socialnetwork")
        else:
            return render(request, 'register.html', {
                'message': 'This username has been registered'
            })
    return render(request, 'register.html')


def global_stream(request):
    cur_user_id = request.session.get('cur_user_id')
    user = User.objects.get(pk=cur_user_id)
    posts = Post.objects.order_by('-post_time').all()
    comments_dict = {}

    global_post_ids = []
    global_comment_ids = []

    for post in posts:
        global_post_ids.append(post.id)
        comments_dict[post.id] = Comment.objects.filter(post=post).all()
        for comment in comments_dict[post.id]:
            global_comment_ids.append(comment.id)

    request.session['global_post_ids'] = global_post_ids
    request.session['global_comment_ids'] = global_comment_ids

    return render(request, 'global-stream.html', {
        'user': user,
        'post_list': posts,
        'comments_dict': comments_dict,
        'last_refresh_time': timezone.now().isoformat()
    })


def follower_stream(request):
    cur_user_id = request.session.get('cur_user_id')
    cur_user = User.objects.get(pk=cur_user_id)
    cur_profile = Profile.objects.filter(user_id=cur_user_id).first()
    posts = Post.objects.filter(poster_id__in=cur_profile.followers.values(
        'user_id')).order_by('-post_time').all()
    comments_dict = {}

    follower_post_ids = []
    follower_comment_ids = []

    for post in posts:
        follower_post_ids.append(post.id)
        comments_dict[post.id] = Comment.objects.filter(post=post).all()
        for comment in comments_dict[post.id]:
            follower_comment_ids.append(comment.id)

    request.session['follower_post_ids'] = follower_post_ids
    request.session['follower_comment_ids'] = follower_comment_ids

    return render(request, 'follower-stream.html', {
        'user': cur_user,
        'post_list': posts,
        'comments_dict': comments_dict,
        'last_refresh_time': timezone.now().isoformat()
    })


def profile(request, user_id=None):
    cur_user_id = request.session.get('cur_user_id')

    user = User.objects.get(pk=cur_user_id)
    cur_profile = None
    target_profile = None
    form = None
    is_following = None

    profiles = Profile.objects.filter(user_id=cur_user_id)

    if profiles.count() == 0:
        cur_profile = Profile(user=user)
        cur_profile.save()
    else:
        cur_profile = profiles.first()

    if user_id != None:
        target_profile = Profile.objects.filter(
            user=User.objects.get(pk=user_id)).first()
        is_following = Profile.objects.get(pk=cur_profile.id).followers.filter(
            pk=target_profile.id).count() != 0

    if request.method == 'POST':
        form = ProfileForm(data=request.POST,
                           files=request.FILES, instance=cur_profile)
        if form.is_valid():
            form.save()
    return render(request, 'profile.html', {
        'user': user,
        'profile': cur_profile,
        'followers': cur_profile.followers.all(),
        'target_profile': target_profile,
        'is_following': is_following
    })


def follow(request, user_id):
    cur_user_id = request.session.get('cur_user_id')
    cur_profile = Profile.objects.filter(user_id=cur_user_id).first()
    target_profile = None
    if user_id != None and cur_user_id != user_id:
        target_profile = Profile.objects.filter(
            user=User.objects.get(pk=user_id)).first()
        cur_profile.followers.add(target_profile)
    return redirect("/socialnetwork/profile/"+user_id)


def unfollow(request, user_id):
    cur_user_id = request.session.get('cur_user_id')
    cur_profile = Profile.objects.filter(user_id=cur_user_id).first()
    target_profile = None
    if user_id != None and cur_user_id != user_id:
        target_profile = Profile.objects.filter(
            user=User.objects.get(pk=user_id)).first()
        cur_profile.followers.remove(target_profile)
    return redirect("/socialnetwork/profile/"+user_id)


def post(request):
    cur_user_id = request.session.get('cur_user_id')
    cur_user = User.objects.get(pk=cur_user_id)
    cur_profile = Profile.objects.filter(user_id=cur_user_id).first()
    if request.method == 'POST':
        post = Post(content=request.POST.get('content'),
                    poster=cur_user)
        post.save()
    return redirect("/socialnetwork/global-stream")


def refresh_global(request):
    cur_user_id = request.session.get('cur_user_id')
    user = User.objects.get(pk=cur_user_id)

    last_refresh = request.GET.get("last_refresh")
    if last_refresh == None:
        raise Http404()

    post_list = []
    for post in Post.objects.filter(post_time__lte=dateutil.parser.parse(last_refresh)).order_by('-post_time').all():
        if post.id in request.session['global_post_ids']:
            continue
        else:
            request.session['global_post_ids'].append(post.id)

        poster = post.poster
        post_list.append({
            'id': post.id,
            'post_content': post.content,
            'post_time': post.post_time.strftime('%b %d, %Y, %I:%M %p').replace('PM', 'p.m.').replace('AM', 'a.m.'),
            'poster': {
                'id': poster.id,
                'first_name': poster.first_name,
                'last_name': poster.last_name
            }
        })

    comments_dict = {}
    for comment in Comment.objects.filter(comment_time__lte=dateutil.parser.parse(last_refresh)).order_by("comment_time").all():
        if comment.id in request.session['global_comment_ids']:
            continue
        else:
            request.session['global_comment_ids'].append(comment.id)

        reviewer = comment.reviewer
        if comments_dict.get(comment.post.id) == None:
            comments_dict[comment.post.id] = []

        comments_dict[comment.post.id].append({
            'id': comment.id,
            'comment_text': comment.comment_text,
            'comment_time': comment.comment_time.strftime('%b %d, %Y, %I:%M %p').replace('PM', 'p.m.').replace('AM', 'a.m.'),
            'reviewer': {
                'id': reviewer.id,
                'first_name': reviewer.first_name,
                'last_name': reviewer.last_name
            },
            'post': {
                'id': comment.post.id
            }
        })

    return JsonResponse({
        'last_refresh_time': timezone.now().isoformat(),
        'post_list': post_list,
        'comments_dict': comments_dict
    })


def refresh_follower(request):
    cur_user_id = request.session.get('cur_user_id')
    user = User.objects.get(pk=cur_user_id)
    cur_profile = Profile.objects.filter(user_id=cur_user_id).first()

    last_refresh = request.GET.get("last_refresh")
    if last_refresh == None:
        raise Http404()

    post_list = []
    comments_dict = {}

    for post in Post.objects.filter(poster_id__in=cur_profile.followers.values('user_id')).filter(post_time__lte=dateutil.parser.parse(last_refresh)).order_by('-post_time').all():
        if post.id in request.session['follower_post_ids']:
            pass
        else:
            request.session['follower_post_ids'].append(post.id)

            poster = post.poster
            post_list.append({
                'id': post.id,
                'post_content': post.content,
                'post_time': post.post_time.strftime('%b %d, %Y, %I:%M %p').replace('PM', 'p.m.').replace('AM', 'a.m.'),
                'poster': {
                    'id': poster.id,
                    'first_name': poster.first_name,
                    'last_name': poster.last_name
                }
            })

        for comment in Comment.objects.filter(post=post).filter(comment_time__lte=dateutil.parser.parse(last_refresh)).order_by("comment_time").all():
            if comment.id in request.session['follower_comment_ids']:
                continue
            else:
                request.session['follower_comment_ids'].append(comment.id)

            reviewer = comment.reviewer
            if comments_dict.get(comment.post.id) == None:
                comments_dict[comment.post.id] = []

            comments_dict[comment.post.id].append({
                'id': comment.id,
                'comment_text': comment.comment_text,
                'comment_time': comment.comment_time.strftime('%b %d, %Y, %I:%M %p').replace('PM', 'p.m.').replace('AM', 'a.m.'),
                'reviewer': {
                    'id': reviewer.id,
                    'first_name': reviewer.first_name,
                    'last_name': reviewer.last_name
                },
                'post': {
                    'id': comment.post.id
                }
            })

    return JsonResponse({
        'last_refresh_time': timezone.now().isoformat(),
        'post_list': post_list,
        'comments_dict': comments_dict
    })


@csrf_exempt
def add_comment(request):
    cur_user_id = request.session.get('cur_user_id')
    cur_user = User.objects.get(pk=cur_user_id)
    cur_profile = Profile.objects.filter(user_id=cur_user_id).first()
    if request.method == 'POST':
        comment = Comment(comment_text=request.POST.get('comment_text'),
                          reviewer=cur_user, post=Post.objects.get(pk=request.POST.get("post_id")))
        comment.save()

    return JsonResponse({})
