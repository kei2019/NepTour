# Generated by Django 2.2.4 on 2019-08-24 16:46

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('neptourapp', '0013_remove_photographer_username'),
    ]

    operations = [
        migrations.AddField(
            model_name='photographer',
            name='password',
            field=models.TextField(default=1, max_length=20),
            preserve_default=False,
        ),
    ]
