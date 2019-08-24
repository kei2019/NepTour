#import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

def get_title_from_index(index):
    return df[df.index==index]["place_name"].values[0]
def get_index_from_title(place_name):
    return df[df.place_name==place_name]["index"].values[0]

df=pd.read_csv("place.csv")

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
#
#
#

cos_sim=cosine_similarity(count_matrix)
liked_place="itahari"

place_index=get_index_from_title(liked_place)
similar_places=list(enumerate(cos_sim[place_index]))

sorted_similar_places=sorted(similar_places, key=lambda x:x[1], reverse=True)
#####

i=0
for place in sorted_similar_places:
    print(get_title_from_index(place[0]))
    i=i+1
    if i>20:
        break




