import pymysql
import pandas as pd
import requests
import json
import sys

def main(access_token, recruit_id, receiver_uuids):
    print(receiver_uuids)
    uuids=receiver_uuids.split('*')
    
    tmp=""
    for i in uuids:
        tmp+='"'+i+'",'
    tmp=tmp[:len(tmp)-1]
    
    conn = pymysql.connect(host='localhost', user='root', password='ssafy', db='test', charset='utf8')
    curs = conn.cursor()
    # sql = "select * from recruits where recruit_id = (select recruit_id from recommend where user_id = "+user_id+" ) "
    sql = "select * from recruits where recruit_id = " + recruit_id

    curs.execute(sql)
    rows = curs.fetchall()
    rows_data = pd.DataFrame(rows)
    #print(rows_data)
    #print(rows_data[2])
    #print(rows_data[4])
    # access_token="DJtWKRhhc9mQaqltzi5ytmH7hCMryOKBFZj0AgopcNIAAAFxrO3kqA"
    like_cnt=100
    title = rows_data[2][0]
    description = rows_data[4][0]
    image_url="http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg"

    template_list_data = str(
        ["h7WEvIy0hbKLp5WlkaOXoZC8iLqJvYS84A"]
    )

    template_dict_data = str({
        "object_type": "feed",
        "content": {
            "title": title,
            "description": description,
            "image_url": image_url,
            "image_width": 640,
            "image_height": 640,
            "link": {
                "web_url": "http://www.daum.net",
                "mobile_web_url": "http://m.daum.net",
                "android_execution_params": "contentId=100",
                "ios_execution_params": "contentId=100"
            }
        },
        "social": {
            "like_count": like_cnt
        },
        "buttons": [
            {
                "title": "웹으로 이동",
                "link": {
                    "web_url": "http://www.daum.net",
                    "mobile_web_url": "http://m.daum.net"
                }
            },
            {
                "title": "앱으로 이동",
                "link": {
                    "android_execution_params": "contentId=100",
                    "ios_execution_params": "contentId=100"
                }
            }
        ]
    })

    kakao_to_me_uri = 'https://kapi.kakao.com//v1/api/talk/friends/message/default/send'


    headers = {
        'Content-Type': "application/x-www-form-urlencoded",
        'Authorization': "Bearer " + access_token,
    }

    template_object = str(json.dumps(template_dict_data))
    template_object = template_object.replace("\"", "")
    template_object = template_object.replace("'", "\"")

    params = {}
    # params['receiver_uuids'] = '["h7WEvIy0hbKLp5WlkaOXoZC8iLqJvYS84A"]'
    # params['receiver_uuids'] = '['+'"'+str(receiver_uuids[0])+'"'+']'
    params['receiver_uuids'] = '['+tmp+']'
    params['template_object'] = template_object

    response = requests.request(method="POST", url=kakao_to_me_uri, params=params, headers=headers)
    print(response.json())

if __name__ == "__main__":
    main(sys.argv[1] , sys.argv[2] , sys.argv[3])