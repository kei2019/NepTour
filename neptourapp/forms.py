from .models import *
from django import forms

class Form(forms.ModelForm):
    username = forms.CharField(widget = forms.TextInput())
    password = forms.CharField(widget = forms.PasswordInput())
    confirm_password = forms.CharField(widget = forms.PasswordInput())

    class Meta:
        model = Photographer
        fields = ['username','password','name','address','email','contact','pimage']


class LoginForm(forms.Form):
    username = forms.CharField(widget=forms.TextInput())
    password = forms.CharField(widget=forms.PasswordInput())


