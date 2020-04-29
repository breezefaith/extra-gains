from django import forms
from socialnetwork.models import Profile

class ProfileForm(forms.ModelForm):
    bio = forms.CharField(required=False)
    class Meta:
        model = Profile
        fields = ['bio', 'img']
