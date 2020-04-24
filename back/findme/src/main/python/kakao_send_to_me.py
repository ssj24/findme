import requests
import json

access_token="DJtWKRhhc9mQaqltzi5ytmH7hCMryOKBFZj0AgopcNIAAAFxrO3kqA"
like_cnt=100
title="채용공고명"
description="필요한 기술스택을 쓸까?"
image_url="http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg"

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

kakao_to_me_uri = 'https://kapi.kakao.com/v2/api/talk/memo/default/send'


headers = {
    'Content-Type': "application/x-www-form-urlencoded",
    'Authorization': "Bearer " + access_token,
}

template_json_data = "template_object="+str(json.dumps(template_dict_data))
template_json_data = template_json_data.replace("\"", "")
template_json_data = template_json_data.replace("'", "\"")
 
response = requests.request(method="POST", url=kakao_to_me_uri, data=template_json_data, headers=headers)
print(response.json())