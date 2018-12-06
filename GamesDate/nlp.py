import pickle
import nltk
import numpy as np
from nltk import NaiveBayesClassifier
from nltk import bigrams
import json
import sys




f = open('D:/GitHub/GameCollector/GamesDate/classifier.pickle', 'rb')
classifier = pickle.load(f)
f.close()
f = open('D:/GitHub/GameCollector/GamesDate/classifier1.pickle', 'rb')
classifier1 = pickle.load(f)
f.close()
f = open('D:/GitHub/GameCollector/GamesDate/classifier2.pickle', 'rb')
classifier2 = pickle.load(f)
f.close()



with open('D:/GitHub/GameCollector/GamesDate/mc_games_data.json') as data_file:
    gameData = json.load(data_file) # gameData review file

documents = []  # 0-10
for key in gameData:
    for i in range(len(gameData[key][4])):
        if str.isdigit(gameData[key][4][i][0]) == True:
            if int(gameData[key][4][i][0]) >= 6:
                documents.append((gameData[key][4][i][1].split(), 'pos'))
            if int(gameData[key][4][i][0]) <= 5:
                documents.append((gameData[key][4][i][1].split(), 'neg'))
    for i in range(len(gameData[key][5])):
        if str.isdigit(gameData[key][5][i][0]) == True:
            if int(gameData[key][5][i][0]) >= 51:
                documents.append((gameData[key][5][i][1].split(), 'pos'))
            if int(gameData[key][5][i][0]) <= 50:
                documents.append((gameData[key][5][i][1].split(), 'neg'))

documents1 = []
for key in gameData:  # 0-5
    for i in range(len(gameData[key][4])):
        if str.isdigit(gameData[key][4][i][0]) == True:
            if int(gameData[key][4][i][0]) in [3, 4, 5]:
                documents1.append((gameData[key][4][i][1].split(), 'pos'))
            if int(gameData[key][4][i][0]) in [0, 1, 2]:
                documents1.append((gameData[key][4][i][1].split(), 'neg'))
    for i in range(len(gameData[key][5])):
        if str.isdigit(gameData[key][5][i][0]) == True:
            if int(gameData[key][5][i][0]) >= 25 and int(gameData[key][5][i][0]) <= 50:
                documents1.append((gameData[key][5][i][1].split(), 'pos'))
            if int(gameData[key][5][i][0]) < 25:
                documents1.append((gameData[key][5][i][1].split(), 'neg'))

documents2 = []  # 6-10
for key in gameData:
    for i in range(len(gameData[key][4])):
        if str.isdigit(gameData[key][4][i][0]) == True:
            if int(gameData[key][4][i][0]) in [9, 10]:
                documents2.append((gameData[key][4][i][1].split(), 'pos'))
            if int(gameData[key][4][i][0]) in [6, 7, 8]:
                documents2.append((gameData[key][4][i][1].split(), 'neg'))
    for i in range(len(gameData[key][5])):
        if str.isdigit(gameData[key][5][i][0]) == True:
            if int(gameData[key][5][i][0]) > 75 and int(gameData[key][5][i][0]) <= 100:
                documents2.append((gameData[key][5][i][1].split(), 'pos'))
            if int(gameData[key][5][i][0]) > 50 and int(gameData[key][5][i][0]) <= 75:
                documents2.append((gameData[key][5][i][1].split(), 'neg'))

reviews=[]
reviews_words = []
for key in gameData:
    for i in range(len(gameData[key][4])):
        if str.isdigit(gameData[key][4][i][0]) == True:
            if int(gameData[key][4][i][0]) >= 6:
                reviews.append(gameData[key][4][i][1].split())
            if int(gameData[key][4][i][0]) <= 5:
                reviews.append(gameData[key][4][i][1].split())
    for i in range(len(gameData[key][5])):
        if str.isdigit(gameData[key][5][i][0]) == True:
            if int(gameData[key][5][i][0]) >= 51:
                reviews.append(gameData[key][5][i][1].split())
            if int(gameData[key][5][i][0]) <= 50:
                reviews.append(gameData[key][5][i][1].split())

for i in range(len(reviews)):
    for j in range(len(reviews[i])):
        reviews_words.append(reviews[i][j]);

reviews1 = []
reviews_words1 = []
for key in gameData:
    for i in range(len(gameData[key][4])):
        if str.isdigit(gameData[key][4][i][0]) == True:
            if int(gameData[key][4][i][0]) <= 5:
                reviews1.append(gameData[key][4][i][1].split())
    for i in range(len(gameData[key][5])):
        if str.isdigit(gameData[key][5][i][0]) == True:
            if int(gameData[key][5][i][0]) <= 50:
                reviews1.append(gameData[key][5][i][1].split())

for i in range(len(reviews1)):
    for j in range(len(reviews1[i])):
        reviews_words1.append(reviews1[i][j]);

reviews2 = []
reviews_words2 = []
for key in gameData:
    for i in range(len(gameData[key][4])):
        if str.isdigit(gameData[key][4][i][0]) == True:
            if int(gameData[key][4][i][0]) >= 6:
                reviews2.append(gameData[key][4][i][1].split())
    for i in range(len(gameData[key][5])):
        if str.isdigit(gameData[key][5][i][0]) == True:
            if int(gameData[key][5][i][0]) >= 51:
                reviews2.append(gameData[key][5][i][1].split())

for i in range(len(reviews2)):
    for j in range(len(reviews2[i])):
        reviews_words2.append(reviews2[i][j]);



np.random.shuffle(documents)
# All words, by frequency
all_words = nltk.FreqDist(w.lower() for w in reviews_words)

# Most frequent 2000 words/tokens
word_features = [w for (w,c) in all_words.most_common(2000)]

# Extracting features from a given document
def document_features(document):
    document_words = set(document)
    features = {}
    for word in word_features:
        features['contains(%s)' % word] = (word in document_words)
    return features

np.random.shuffle(documents1)  #0-5
# All bigrams, by frequency
all_words1 = nltk.FreqDist(w.lower() for w in reviews_words1)
# Most frequent 2000 bigrams
word_features1 = [w for (w,c) in all_words1.most_common(2000)]
# Extract the features
def document_features1(document):
    document_words = set(document)
    features = {}
    for word in word_features1:
        features['contains(%s)' % word] = (word in document_words)
    return features

np.random.shuffle(documents2)  #6--10
# All bigrams, by frequency
all_words2 = nltk.FreqDist(w.lower() for w in reviews_words2)
# Most frequent 2000 bigrams
word_features2 = [w for (w,c) in all_words2.most_common(2000)]
# Extract the features
def document_features2(document):
    document_words = set(document)
    features = {}
    for word in word_features2:
        features['contains(%s)' % word] = (word in document_words)
    return features



def get_score(t):
    value=classifier.classify(document_features(t.split()))#判断属于哪个区间0-5 还是6-10
    if value == "neg":
        result=classifier2.classify(document_features2(t.split()))
        if result== "pos":
            print("normal")
        else:
            print ("bad")
    else:
        result=classifier1.classify(document_features1(t.split()))
        if result== "pos":
            print("great")
        else:
            print ("good")

if __name__ == "__main__":
    t = sys.argv[1]
    get_score(t)
    # t = "so an addictive game!"
    # get_score(t)
    #
    # t = 'this game is unforgivably bad'
    # get_score(t)
    #
    # t = 'this is one of my favourite games of the year and it is one I would encourage any previous Final Fantasy fan to consider'
    # get_score(t)
    #
    #
    # t=" This game has a cool concept but doesnt pool it off well and overall gets boring after about an hour and even quicker when you play， so Bad"
    # get_score(t)