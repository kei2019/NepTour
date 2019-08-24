import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

def get_title_from_index(District_code):
    return df[df.District_code==District_code]["VDC_name"].values[0]
def get_index_from_title(VDC_name):
    return df[df.VDC_name==VDC_name]["District_code"].values[0]

