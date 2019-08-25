from django.urls import path, include
from .views import *
from . import views

app_name = 'neptourapp'

urlpatterns = [

    path('navbar/', Home.as_view(), name='navbar'),
    path('', HomeView.as_view(), name='home'),
    path('api/', ApiCreateView.as_view(), name='apicreate'),
    path('api/<int:pk>/', APIRetreiveDestroyDelete.as_view(),
         name='apiretrievedestroydelete'),
    path('registerme/', Registration.as_view(), name='registration'),
    path("login/", LoginView.as_view(), name='login'),
    path('add/post/',AddPost.as_view(),name = 'addpost'),
    path('profile/', ProfileView.as_view(),name = 'profile'),

    path('search/', PlaceRecommendation.search, name='search'),
    path('package/', Package.as_view(), name='package'),
    path('donation/', Donation.as_view(), name='donation'),



    path('recommendation/', RecommendationView.as_view()),
    path('locations/', FetchAllView.as_view()),

    # path('searchview/', SearchView.as_view(), name='searchview'),

    # path("login-in/", views.Loginview, name='login'),




]

