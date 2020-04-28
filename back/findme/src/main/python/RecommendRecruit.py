import pymysql
import pandas as pd
from math import sqrt
import sys

pd.set_option('display.max_rows', 500)
pd.set_option('display.max_columns', 500)
pd.set_option('display.width', 1000)

picks = {
    '1': {
        '34393991': 1,
        '37741669': 1,
        '37737338': 1,
        '37748077': 1,
        '37797590': 1
    },
    '2': {
        '38054544': 1,
        '37938159': 1,
        '37891619': 1,
        '38039063': 1,
        '38031361': 1,
        '38055447': 0,
    },
    '3': {
        '38054544': 0,
        '37938159': 0,
        '37891619': 1,
        '38039063': 0,
        '38055447': 1,
    }
}

def sim_pearson(data, userId1, userId2):
    # sumX = 0  # X의 합
    # sumY = 0  # Y의 합
    # sumPowX = 0  # X 제곱의 합
    # sumPowY = 0  # Y 제곱의 합
    # sumXY = 0  # X*Y의 합
    # count = 0  # 영화 개수
    #
    # for i in data[userId1]:  # i = key
    #     if i in data[userId2]:  # 같은 영화를 평가했을때만
    #         sumX += data[userId1][i]
    #         sumY += data[userId2][i]
    #         sumPowX += pow(data[userId1][i], 2)
    #         sumPowY += pow(data[userId2][i], 2)
    #         sumXY += data[userId1][i] * data[userId2][i]
    #         count += 1
    #
    # # print("sumX: " + str(sumX))
    # # print("sumY: " + str(sumY))
    # # print("sumPowX: " + str(sumPowX))
    # # print("sumPowY: " + str(sumPowY))
    # # print("sumXY: " + str(sumXY))
    # # print("count: " + str(count))
    # # print("((sumX * sumY) / count): " + str(((sumX * sumY) / count)))
    # # print("pow(sumX, 2): " + str(pow(sumX, 2)))
    # try:
    #     return (sumXY - ((sumX * sumY) / count)) / sqrt(
    #     (sumPowX - (pow(sumX, 2) / count)) * (sumPowY - (pow(sumY, 2) / count)))
    # except:
    #     return 0
    avg_id1 = 0
    avg_id2 = 0
    count = 0
    for tech in data[userId1]:
        if tech in data[userId2]:  # 같은 영화를 봤다면
            avg_id1 = data[userId1][tech]
            avg_id2 = data[userId2][tech]
            count += 1
    try:
        avg_id1 = avg_id1 / count
    except:
        avg_id1 = 0
    try:
        avg_id2 = avg_id2 / count
    except:
        avg_id2 = 0

    sum_id1 = 0
    sum_id2 = 0
    sum_id1_id2 = 0
    count = 0
    for tech in data[userId1]:
        if tech in data[userId2]:
            sum_id1 += pow(data[userId1][tech] - avg_id1, 2)
            sum_id2 += pow(data[userId2][tech] - avg_id2, 2)
            sum_id1_id2 += (data[userId1][tech] - avg_id1) * (data[userId2][tech] - avg_id2)
    try:
        return sum_id1_id2 / (sqrt(sum_id1) * sqrt(sum_id2))
    except:
        return 0

def top_match(data, userId, index=10, sim_function=sim_pearson):
    li=[]
    for i in data: #딕셔너리를 돌고
        if userId != i: #자기 자신이 아닐때만
            li.append((sim_function(data, userId, i), i)) #sim_function()을 통해 상관계수를 구하고 li[]에 추가
    li.sort() #오름차순
    li.reverse() #내림차순
    return li[:index]

def getRecommendation(data, myId, sim_function=sim_pearson):
    result = top_match(picks, myId)
    print(result)
    simSum = 0  # 유사도 합을 위한 변수
    score = 0  # 평점 합을 위한 변수
    li = []  # 리턴을 위한 리스트
    score_dic = {}  # 유사도 총합을 위한 dic
    sim_dic = {}  # 평점 총합을 위한 dic

    for sim, otherId in result:  # 튜플이므로 한번에
        if sim <= 0:
            continue  # 유사도가 양수인 사람만
        for recruit in data[otherId]:
            if data[otherId][recruit] == 1 and recruit not in data[myId]: # 내 찜 목록에 없는데 그 사람 찜 목록에 있으면
                score += sim * data[otherId][recruit]
                score_dic.setdefault(recruit, 0)  # 기본값 설정
                score_dic[recruit] += score  # 합계 구함

                sim_dic.setdefault(recruit, 0)
                sim_dic[recruit] += sim
                continue
            if (data[myId][recruit] == 0 or recruit not in data[myId]) and data[otherId][recruit] == 1:  # 나는 찜 안 했는데 그 사람은 찜한 목록
            # if recruit in data[myId] and data[otherId][recruit] == 1:  # 나는 찜 안 했는데 그 사람은 찜한 목록
                score += sim * data[otherId][recruit]  # 그 사람의 찜 공고
                score_dic.setdefault(recruit, 0)  # 기본값 설정
                score_dic[recruit] += score  # 합계 구함

                # 조건에 맞는 사람의 유사도의 누적합을 구한다
                sim_dic.setdefault(recruit, 0)
                sim_dic[recruit] += sim

            score = 0  # 공고가 바뀌었으니 초기화한다

    for key in score_dic:
        score_dic[key] = sim_dic[key] != 0 and score_dic[key] / sim_dic[key] or 1.0  # 평점 총합 / 유사도 총합
        li.append((score_dic[key], key))  # list((tuple))의 리턴을 위해서.
    li.sort()  # 오름차순
    li.reverse()  # 내림차순
    return li

# print(top_match(picks, '1'))

# print(getRecommendation(picks, '2'))

def start(userId):
    userId = int(userId)

    # 파이썬-MySQL 연결
    # MySQL 설정
    HOST = '127.0.0.1'
    PORT = 3306
    USER = 'root'
    PASSWORD = 'ssafy'
    DB = 'findme'

    # MySQL Connection 연결
    conn = pymysql.connect(host=HOST, port=PORT, user=USER, password=PASSWORD, db=DB)

    # Connection 으로부터 Cursor 생성
    curs = conn.cursor()

    # 찜 데이터 수집
    sql = "select user_id, recruit_id from tmppick order by user_id"

    # SQL 문 실행
    curs.execute(sql)

    pickRows = curs.fetchall()
    tmpDataFrame = pd.DataFrame(pickRows).set_index(0)

    # 전체 공고 아이디 수집
    sql = "select number from recruit"

    # SQL 문 실행
    curs.execute(sql)

    recruitRows = curs.fetchall()

    recruitIdList = []

    for recruitId in recruitRows:
        recruitIdList.append(recruitId[0])

    # recruitIdList.insert(0, 'id')
    print(recruitIdList)

    data = []
    tmp = []

    # print(pickRows[0][1])

    # print(pickRows)

    # for pickRow in pickRows:
    #     if len(data) == 0:
    #         data.append([pickRow[0]])
    #     else:
    #         for d in data:
    #             flag = False
    #
    #             if pickRow[0] not in d:
    #                 flag = True
    #             else:
    #                 flag = False
    #         if flag:
    #             data.append([pickRow[0]])
    #
    # for recruitId in recruitIdList:


    # for pickRow in pickRows:
    #     tmp = {}
    #     data[pickRow[0]] = tmp[pickRow


    print(data)
    #     for recruitId in recruitIdList:
    #         # print(pickRows[i][1])
    #         # print(recruitId)
    #
    #         if str(pickRows[i][1]) == recruitId:
    #             data[i].append(1)
    #         else:
    #             data[i].append(0)
    print(data)
    newData = []

    # dataFrame = pd.DataFrame(data=data)
    # dataFrame.columns = ['id'] + recruitIdList
    # dataFrame.join(dataFrame, on='id', how='left')
    # dataFrame = dataFrame.set_index(['id'])
    # print(dataFrame)
    # dataDict = dataFrame.set_index('id').T.to_dict('dict')
    # print(dataDict)

if __name__=='__main__':
    start(3)
    sys.exit(220)
