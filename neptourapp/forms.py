from .models import *
from django import forms
from django.contrib.auth.forms import UserCreationForm, UserChangeForm


# class CustomUserCreationForm(UserCreationForm):

#     class Meta(UserCreationForm):
#         model = Photographer
#         fields = ('email')

# class CustomUserChangeForm(UserChangeForm):

#     class Meta:
#         model = Photographer
#         fields = ('email')

class Form(forms.ModelForm):
    # username = forms.CharField(widget = forms.TextInput())
    # password = forms.CharField(widget = forms.PasswordInput())
    # confirm_password = forms.CharField(widget = forms.PasswordInput())
    password = forms.CharField(widget=forms.PasswordInput)

    class Meta:
        model = Photographer
        fields = ['name','address','email','contact','pimage','choice','password']

    
    # def clean_email(self):
    #     email = self.cleaned_data.get('email')
    #     email = User.objects.filter(email=email)
    #     if email.exists():
    #         raise forms.ValidationError(
    #             "email already exists")
    #     return email


    # def clean_confirm_password(self):
    #     password = self.cleaned_data.get("password")
    #     c_password = self.cleaned_data.get("confirm_password")
    #     if password != c_password:
    #         raise forms.ValidationError("password didnot match")
    #     return c_password


class LoginForm(forms.Form):
    email = forms.EmailField(widget=forms.TextInput())
    password = forms.CharField(widget=forms.PasswordInput())


class PostForm(forms.ModelForm):
    photographer = forms.CharField(widget=forms.TextInput())

    class Meta:
        model = Post
        fields = ['photographer','description','title','image','place']

