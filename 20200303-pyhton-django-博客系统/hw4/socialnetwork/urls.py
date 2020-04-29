from django.urls import path
from django.conf.urls.static import static
from . import views

urlpatterns = [
    path("", views.index),
    path("login", views.login),
    path("logout", views.logout),
    path("register", views.register),
    path("global-stream", views.global_stream),
    path("follower-stream", views.follower_stream),
    path("profile", views.profile),
    path("profile/<user_id>", views.profile),
    path("follow/<user_id>", views.follow),
    path("unfollow/<user_id>", views.unfollow),
    path("post", views.post),
    path("refresh-global", views.refresh_global),
    path("refresh-follower", views.refresh_follower),
    path("add-comment", views.add_comment),
    
]
