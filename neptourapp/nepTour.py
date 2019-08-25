import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

def get_title_from_index(index):
    return df[df.index==index]["place_name"].values[0]
def get_index_from_title(place_name):
    return df[df.place_name==place_name]["index"].values[0]

# try:
df= pd.read_csv("place.csv")
# except :
    # print("error reading csv")

#print(df.head())
features=['Zone', 'District', 'place_type']

for feature in features:
    df[feature]=df[feature].fillna(' ')

def combine_features(row):
    return row['Zone']+" "+row['District']+" "+row['place_type']

df["combine_features"]=df.apply(combine_features, axis=1)
#print (df["combine_features"].head())

cv=CountVectorizer()
count_matrix=cv.fit_transform(df["combine_features"])


cos_sim=cosine_similarity(count_matrix)

def get_recommendation(liked_place, limit):
    place_index=get_index_from_title(liked_place)

    similar_places=list(enumerate(cos_sim[place_index]))

    sorted_similar_places=sorted(similar_places, key=lambda x:x[1], reverse=True)

    # print 5 recommendations
    def generate_list(list):
        i=0
        res_list = []
        for place in sorted_similar_places:
            res_list.append(get_title_from_index(place[0]))
            i=i+1
            # set no. of recommendations
            if i>limit:
                break
        return res_list

    return generate_list(sorted_similar_places)

def look_for(place_title):
    try:
        m = get_index_from_title(place_title)
        flag = True
    except:
        flag = False
    return flag

def fetch_all():
    df = pd.read_csv("place.csv")

    df = df.fillna('')

    row_list = []
    for index, rows in df.iterrows():
        location_list = {'zone':rows.Zone, 'district':rows.District,'place_name': rows.place_name,
        'place_type':rows.place_type, 'lat':rows.lat, 'lng':rows.lng, 'img':rows.img}

        row_list.append(location_list)

    return row_list

# print(len(fetch_all()))
# found = look_for("Fikkal")
# print(found)
# place_list = get_recommendation("Fikkal", 10)
# print(place_list)
