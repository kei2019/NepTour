# Generated by Django 2.2.4 on 2019-08-24 09:20

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('neptourapp', '0002_auto_20190824_0739'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='post',
            name='photo_name',
        ),
        migrations.AddField(
            model_name='post',
            name='description',
            field=models.TextField(default=1, max_length=500),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='post',
            name='title',
            field=models.TextField(default=1, max_length=200),
            preserve_default=False,
        ),
    ]
