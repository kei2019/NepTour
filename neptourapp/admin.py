from django.contrib import admin
from .models import *

admin.site.register([Photographer, Tourist, Photo,
                     Rating, Package, Post_Type, Post])

# Register your models here.
