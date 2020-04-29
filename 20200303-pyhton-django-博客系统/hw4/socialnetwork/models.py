from django.db import models
from django.contrib.auth.models import User
from django.utils import timezone


class Post(models.Model):
    content = models.TextField()
    poster = models.ForeignKey(User, on_delete=models.CASCADE)
    post_time = models.DateTimeField(default=timezone.now)


class Profile(models.Model):
    bio = models.TextField()
    img = models.ImageField(upload_to='profile_img/%Y/%m/%d',
                            default="profile_img/default_icon.png")
    user = models.OneToOneField(
        User, on_delete=models.CASCADE, to_field='id')
    followers = models.ManyToManyField(
        'self', related_name='following', symmetrical=False)


class Comment(models.Model):
    comment_text = models.TextField()
    reviewer = models.ForeignKey(User, on_delete=models.CASCADE)
    comment_time = models.DateTimeField(default=timezone.now)
    post = models.ForeignKey(Post, on_delete=models.CASCADE)
