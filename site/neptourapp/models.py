from django.db import models
from django.contrib.auth.models import User, Group


# Create your models here.




Choice = (
    ('Tourist', "Tourist"),
    ('Photographer', 'Photographer'),
    


)
class Photographer(models.Model):
    # username = models.OneToOneField(User,on_delete = models.CASCADE)
    name = models.CharField(max_length = 150)
    # Age = models.IntegerField()
    address = models.CharField(max_length = 160)
    # language = models.CharField(max_length = 200)
    email = models.EmailField()
    contact = models.IntegerField()
    choice = models.CharField(max_length = 100,choices = Choice)
    pimage = models.FileField(upload_to = 'photographer',blank = True)
    password = models.TextField(max_length = 20)
    

    # def save(self, *args, **kwargs):
    #     group, created = Group.objects.get_or_create(name='photographer')
    #     self.user.groups.add(group)
    #     super().save(*args, **kwargs)


    def __str__(self):
        return self.name


Choice1 = (
    ('Tourist', "Tourist"),
    ('Photographer', 'Photographer'),
    


)


class Tourist(models.Model):
    # username = models.OneToOneField(User,on_delete = models.CASCADE)
    name = models.CharField(max_length = 150)
    email = models.EmailField(max_length = 20)
    country = models.CharField(max_length = 10)
    choice = models.CharField(max_length = 100,null = True,choices = Choice)

    # dob = models.CharField(max_length = 20)
    pimage = models.FileField(upload_to = 'photographer')

    def save(self, *args, **kwargs):
        group, created = Group.objects.get_or_create(name='tourist')
        self.user.groups.add(group)
        super().save(*args, **kwargs)


    def __str__(self):
        return self.name


# class Photo(models.Model):
#     photographer = models.ForeignKey(Photographer,on_delete = models.CASCADE)

#     def __str__(self):
#         return self.photographer.name



class Rating(models.Model):
    photographer = models.ForeignKey(Photographer,on_delete = models.CASCADE)

    def __str__(self):
        return self.photographer.name

class Package(models.Model):
    name = models.CharField(max_length=100)
    place = models.CharField(max_length=100)
    description = models.TextField(max_length=200)
    cost =models.IntegerField()
    pack_start_date = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.name

class Post_Type(models.Model):
    post = models.TextField(max_length=50)

    def __str__(self):
        return self.post


class Post(models.Model):
    # photographer_name = models.ForeignKey(Photographer,on_delete = models.CASCADE,null = True,blank = True)
    photographer = models.TextField(max_length = 30)
    description = models.CharField(max_length=500)
    title = models.CharField(max_length=200)
    image = models.FileField(upload_to='post')
    place = models.CharField(max_length = 30)

    def __str__(self):
        return self.title


