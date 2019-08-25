from django.contrib import admin
from .models import *
from django.contrib import admin
from django.contrib.auth import get_user_model
from django.contrib.auth.admin import UserAdmin



# from .forms import CustomUserCreationForm, CustomUserChangeForm


# class CustomUserAdmin(UserAdmin):
#     add_form = CustomUserCreationForm
#     form = CustomUserChangeForm
#     model = Photographer
#     list_display = ['email', 'username']

# admin.site.register(Photographer, CustomUserAdmin)





admin.site.register([Photographer, Tourist, 
                     Rating, Package, Post_Type, Post])

# Register your models here.
