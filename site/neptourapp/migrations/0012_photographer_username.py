# Generated by Django 2.2.4 on 2019-08-24 16:26

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('neptourapp', '0011_auto_20190824_1609'),
    ]

    operations = [
        migrations.AddField(
            model_name='photographer',
            name='username',
            field=models.OneToOneField(default=1, on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL),
            preserve_default=False,
        ),
    ]
