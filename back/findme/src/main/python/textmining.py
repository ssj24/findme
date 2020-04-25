import sys
import pymysql
from sqlalchemy import create_engine
import pandas as pd
from konlpy.tag import Okt
from collections import Counter
from pandas import DataFrame
# import matplotlib.pyplot as plt
# from PIL import Image
# from wordcloud import WordCloud
# import numpy as np

language = (
        'Java',
        'C',
        'Python',
        'C++',
        'C#',
        'VB.NET',
        'JavaScript',
        'PHP',
        'SQL',
        'Go',
        'R',
        'Assembly',
        'Swift',
        'Ruby',
        'MATLAB',
        'PL/SQL',
        'Perl',
        'Visual Basic',
        'Objective-C',
        'Delphi'
    )

filtering = ["기간", "사용", "진짜"]


# survey + review 데이터 합치기
def makeup_survey(arr, data):
    # print(data)

    for d in data[3]:
        if d == 1: arr.append("불편함")
        elif d == 2: arr.append("배우기어렵다")
        elif d == 3: arr.append("낮은수요")
        elif d == 4: arr.append("개발속도느림")
        else: arr.append("실행속도느림")

    for d in data[5]:
        if d == 1: arr.append("편리함")
        elif d == 2: arr.append("다수사용")
        elif d == 3: arr.append("사용기간오래됨")
        elif d == 4: arr.append("취업에필요")
        else: arr.append("특정 프레임워크를 사용하는 데 필요하다")

    for d in data[1]:
        if d == 1: arr.append("사용편리")
        elif d == 2: arr.append("배우기 쉽다")
        elif d == 3: arr.append("높은수요")
        elif d == 4: arr.append("개발속도빠름")
        else: arr.append("실행속도빠름")

    for d in data[4]:
        arr.append(d)

    return arr



# 형태소 분석
def get_tags(text, ntags=50):
    spliter = Okt()
    line_tag = []
    for line in text:
        morph = spliter.pos(line)
        line_tag.append(morph)
        # print(morph)
        # print('-'*30)

    # print(line_tag)
    # print(len(line_tag))
    # print('\n'*3)

    noun_adj_list = []
    for sentence1 in line_tag:
        for word, tag in sentence1:
            if tag in ['Noun','Adjective'] and (word not in filtering) and len(word) > 1:
                noun_adj_list.append(word)
            if tag in ['Alpha', 'Adjective'] and (word not in filtering) and len(word) > 1:
                noun_adj_list.append(word)

    counts = Counter(noun_adj_list)
    # print(counts.most_common(20))
    # words = dict(counts.most_common(20))
    # print(words)

    result = ""
    for t in counts:
        if len(t) > 1 and (t not in filtering):
            result += t + "," + str(counts[t]) + ","

    result = result[:len(result)-1]
    return result

 

def main():
    # 빈 Dataframe 생성
    df = DataFrame(columns=("id", "name", "result"))
    engine = create_engine("mysql+pymysql://root:"+"ssafy"+"@localhost:3306/test2?charset=utf8", encoding='utf-8')
    con = engine.connect()

    conn = pymysql.connect(host='localhost', user='root', password='ssafy', db='test2', charset='utf8')
    
    # Connection 으로부터 Cursor 생성
    curs = conn.cursor()

    for i in range(1,21):
        tags = ""
        try:
            # SQL문 실행
            sql = "select * from review where language_id =" + str(i)
            curs.execute(sql)
            # 데이타 Fatch
            rows = curs.fetchall()
            review_data = pd.DataFrame(rows)

            contents = []
            for text in review_data[1]:
                contents.append(text)
        except KeyError:
            print(language[i-1]+"에 대해 작성된 리뷰가 없습니다")
            print('-'*30)
        

        try:
            sql = "select * from survey where language_id =" + str(i)
            curs.execute(sql)

            rows = curs.fetchall()
            survey_data = pd.DataFrame(rows)
            total_contents = makeup_survey(contents, survey_data)
            tags = get_tags(total_contents)
        except KeyError:
            print(language[i-1]+"에 대해 작성된 설문이 없습니다")
            print('-'*30)
            if contents is not None:
                tags = get_tags(contents)


        df.loc[i-1] =[i, language[i-1], tags]
        
    print(df)
    df.to_sql('textmining', con, if_exists='replace', index=False, index_label="id")
    conn.commit()
    conn.close()

    # wordcloud
    # mask = np.array(Image.open("alice.png"))
    # print(mask)

    # #텍스트를 읽어주시면 됩니다.
    # text = tags
    
    # # 그리고 다음과 같이 WordCloud 객체를 만들어 주시고
    # wc = WordCloud(font_path = 'C:/Windows/Fonts/malgun.ttf',background_color="white", max_words=20000, 
    # mask=mask, max_font_size=300).generate_from_frequencies(text)

    # plt.imshow(wc, interpolation='bilinear')
    # plt.axis('off')
    # plt.show()
 
if __name__ == '__main__':
    main()