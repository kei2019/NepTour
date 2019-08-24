from rest_framework import serializers
from .models import *


class PhotographerSerializer(serializers.ModelSerializer):
    class Meta:
        fields = '__all__'
        model = Photographer



