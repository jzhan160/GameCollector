# =====================================================================================

# mc_games_data.json是一个包含了所有游戏在Metacritic网站上关于其细节的字典
# 其结构为：
#       - {"gameName": "releaseTime", "mediaScore", "gamerScore", "gameGenre", "userReviews", "criticReviews"}
# 数据类型为：   |           |             |             |            |            |              |
#       - { string:     string,        string,       string,       list,        list,          list}
# 其中，gameGenre为一个包含了多个游戏类型的list
# userReviews与criticreviews分别是玩家评价与媒体评价，它们都是一个list，它们的结构为：
# [["score", "review"], ["score", "review"], ...]
# 数据类型为：[[string, string]]
# 如何使用：
import json
import sys

def getReview(game):
    with open('D:/GitHub/GameCollector/GamesDate/mc_games_data.json') as data_file:
      gameData = json.load(data_file) # gameData 现在就是一个从文件读取的字典
    print("Release Time of "+game+ " is: " + gameData[game][0])
    print("MediaScore: " + gameData[game][1])
    print("GamerScore: " + gameData[game][2])
    print("GameGenre: " + str(gameData[game][3]))
    print("One of user's scrore and review: " + gameData[game][4][0][0] + " " + gameData[game][4][0][1])
    print("One of media's scrore and review: " + gameData[game][5][0][0] + " " + gameData[game][5][0][1])


if __name__ == "__main__":
   game =sys.argv[1]
   getReview(game)