#import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

def get_title_from_index(index):
    return df[df.index==index]["VDC_name"].values[0]
def get_index_from_title(VDC_name):
    return df[df.VDC_name==VDC_name]["index"].values[0]

df=pd.read_csv("vdc.csv")

#print(df.head())
features=['Region', 'Geographical Region', 'District', 'Zone']

#for feature in features:
 #   df[feature]=df[feature].fillna(' ')

def combine_features(row):
    return row['Region']+" "+row['Geographical Region']+" "+row['District']+" "+row['Zone']

df["combine_features"]=df.apply(combine_features, axis=1)
print (df["combine_features"].head())

cv=CountVectorizer()
count_matrix=cv.fit_transform(df["combine_features"])
#
#
#

cos_sim=cosine_similarity(count_matrix)
liked_place="Ankhop"

place_index=get_index_from_title(liked_place)
similar_places=list(enumerate(cos_sim[place_index]))

sorted_similar_places=sorted(similar_places, key=lambda x:x[1], reverse=True)
#####

i=0
for place in sorted_similar_places:
    print(get_title_from_index(place[0]))
    i=i+1
    if i>10:
        break




