import pandas as pd
from pytrends.request import TrendReq
from sqlalchemy import create_engine
# import csv

engine = create_engine("mysql+pymysql://root:"+"ssafy"+"@localhost:3306/findme?charset=utf8", encoding='utf-8')
conn = engine.connect()

pytrends = TrendReq(hl='ko', tz=540)
languages = [
    'Java',
	'C',
	'Python',
	'C++',
	'C#',
	'Visual Basic .NET',
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
	'Delphi/Object'
]

keywords = []
    
for j in range(0, 5):
    keywords.append(languages[j])    
    
pytrends.build_payload(keywords, cat=0, timeframe='today 1-m', geo='KR', gprop='')
getdatainfo = pytrends.interest_over_time()
del getdatainfo['isPartial']

for i in range(1, 4):
    keywords = []
    start = i*5
    for j in range(start, start+5):
        keywords.append(languages[j])    
    
    pytrends.build_payload(keywords, cat=0, timeframe='today 1-m', geo='KR', gprop='')
    getdata = pytrends.interest_over_time()
    for k in range(len(keywords)):
        getdatainfo[keywords[k]] = getdata[keywords[k]]
getdatainfo = getdatainfo.reset_index().rename(columns={"date": "insert_date"})


getdatainfo.to_sql(name='gtrend_data', con=engine, if_exists='append', index=False)
conn.close()

# csv파일로 저장
# getdatainfo.to_csv("language_data"+i+".csv", encoding='utf-8')

# 지역별로 출력
# getdatainfo = pytrends.interest_by_region(resolution='COUNTRY', inc_low_vol=True, inc_geo_code=False) 