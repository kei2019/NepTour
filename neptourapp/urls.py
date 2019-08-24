from django.urls import path, include
from .views import *
from . import views

app_name = 'neptourapp'

urlpatterns = [

    path('', Home.as_view(), name='home'),
    path('api/',ApiCreateView.as_view(),name = 'apicreate'),
    path('api/<int:pk>/',APIRetreiveDestroyDelete.as_view(),name = 'apiretrievedestroydelete'),
    path('register/',Registration.as_view(),name = 'registration'),
    # path('login/',Login.as_view(),name = login),


]
