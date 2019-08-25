from django.shortcuts import render
from django.views.generic import *
from rest_framework.generics import *
from .models import *
from .serializers import *
from rest_framework import generics
from .forms import Form, LoginForm, PostForm
from django.contrib.auth import login, authenticate, logout
from django.http import HttpResponse
from . import forms

from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import generics

from . import nepTour


# Create your views here.
class Home(TemplateView):
    template_name = "navbar.html"


class HomeView(TemplateView):
    template_name = "myhome.html"

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['posts'] = Post.objects.all()
        return context


class ApiCreateView(ListCreateAPIView):
    queryset = Photographer.objects.all()
    serializer_class = PhotographerSerializer


class APIRetreiveDestroyDelete(RetrieveUpdateDestroyAPIView):
    queryset = Photographer.objects.all()
    serializer_class = PhotographerSerializer


class RecommendationView(APIView):
    def get(self, request):
        liked_place = request.GET["liked_place"]
        flag = nepTour.look_for(liked_place)
        if flag == True:
            return Response({"recommendation": nepTour.get_recommendation(liked_place, 20)})
        else:
            return "Error"


class FetchAllView(APIView):
    def get(self, request):
        return Response({"locations": nepTour.fetch_all()})


class Registration(CreateView):
    template_name = 'registration.html'
    form_class = Form
    success_url = '/'

    def form_valid(self, form):
        email = form.cleaned_data['email']
        pword = form.cleaned_data['password']
        user = User.objects.create_user(email, '', pword)
        form.instance.user = user
        # login(self.request, user)
        return super().form_valid(form)


class LoginView(FormView):
    template_name = 'login.html'
    form_class = LoginForm
    success_url = '/'

    def form_valid(self, form):
        email = form.cleaned_data['email']
        pword = form.cleaned_data['password']
        user = authenticate(username=email, password=pword)
        login(self.request, user)
        return super().form_valid(form)


class AddPost(CreateView):
    template_name = 'addpost.html'
    form_class = PostForm
    success_url = '/'


class ProfileView(TemplateView):
    template_name = 'profile.html'
    # model = Photographer
    # context_object_name = 'profile'

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        # log_user = self.request.user
        # photographer = Photographer.objects.get(user=log_user)
        context['profiles'] = Photographer.objects.all()
        return context


class PlaceRecommendation():
    # template_name = 'search.html'
    # form_class = SearchForm

    def search(request):
        form = forms.SearchForm

        # recommendation list
        recommended_list = []
        flag = "null"

        if request.method == 'POST':
            form = forms.SearchForm(request.POST)

            if form.is_valid():
                title = form.cleaned_data['title']
                flag = nepTour.look_for(title)

                if flag == False:
                    print("No such Place in database")
                else:
                    recommended_list = nepTour.get_recommendation(title, 5)
                    print(recommended_list)

        return render(request, 'search.html', {"form": form, "flag": flag, "recommended_list": recommended_list, })

# class SearchView(TemplateView):
#     template_name = "seacrh.html"
#     def get_context_data(self,**kwargs):
#         context = super().get_context_data(**kwargs)
#         keyword = self.request.GET.get('q')
#
#         flag = nepTour.look_for(keyword)
#         recommended_list = []
#         if flag == False:
#             print("No such Place in database")
#         else:
#             recommended_list = nepTour.get_recommendation(keyword)
#             print(recommended_list)
#        return


class Package(TemplateView):
    template_name = 'package.html'


class Donation(TemplateView):
    template_name = 'donation.html'

# def loginview(request):
#     if request.method == 'POST':
#         form = AuthenticationForm(data=request.POST)
#         if form.is_valid():
#             user = form.get_user()
#             login(request, user)
#             return redirect('/website/profile/')
#     else:
#         form = AuthenticationForm()
#     return render(request, 'website/login.html', {'form': form})

    # def form_valid(self,form):
    #     email = form.cleaned_data['email']
    #     pword = form.cleaned_data['password']
    #     user = authenticate(email=email, password=pword)
    #     self.thisuser = user
    #     if user is not None:
    #         if user.is_active:

    #             login(self.request, user)
    #     else:
    #         return render(self.request, self.template_name, {
    #             'error': 'your email doesnot exist',
    #             'form': form
    #         })
    #     return super().form_valid(form)


# class Login(FormView):
#     template_name = 'login.html'
#     form_class = LoginForm
#     success_url = '/'

#     def form_valid(self,form):
#         uname = form.cleaned_data['username']
#         pword = form.cleaned_data['password']

#         user = authenticate(username=uname, password=pword)
#         self.thisuser = user
#         if user is not None and user.groups.exists():
#             login(self.request, user)
#         else:
#             return render(self.request, self.template_name, {
#                 'error': 'your username doesnot exist',
#                 'form': form
#             })
#         return super().form_valid(form)
