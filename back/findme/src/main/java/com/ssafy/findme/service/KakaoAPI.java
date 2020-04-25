package com.ssafy.findme.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoAPI {

	public String getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=df3683c5354024c47b509ecad955f714");
			sb.append("&redirect_uri=http://localhost:8888/api/user/kakao_oauth");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}

	public HashMap<String, Object> getUserInfo(String access_Token) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("email", email);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return userInfo;
	}

	public void kakaoLogout(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void profile(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/api/talk/profile";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 친구목록받기
	public HashMap<String, Object> friends(String access_Token) {
		HashMap<String, Object> friendsInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v1/api/talk/friends";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			int friends_cnt = element.getAsJsonObject().get("total_count").getAsInt();
//			JsonArray elements = element.getAsJsonObject().get("elements").getAsJsonArray();
			System.out.println("friends_cnt: " + friends_cnt);
//			System.out.println("elements의 수: "+elements.size());

			friendsInfo.put("friends_cnt", friends_cnt);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return friendsInfo;
	}

	public void sendToMe(String access_Token, Long recruit_id) {
		CommandLineExecutor.execute("python src/main/python/kakaoSendToMe.py " + access_Token + " " + recruit_id + " ");
	}

	public void sendToFriends(String access_Token, Long recruit_id) {
		CommandLineExecutor
				.execute("python src/main/python/kakaoSendToFriends.py " + access_Token + " " + recruit_id + " ");
	}

	public void sendMessagejorok(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			// 임시값
			String header_title = "WEEKELY MAGAZINE";
			String web_url = "http://www.daum.net";
			String mobile_web_url = "http://m.daum.net";
			String android_execution_params = "main";
			String ios_execution_params = "main";

			JSONObject obj = new JSONObject();
			try {
				obj.put("object_type", "list");
				obj.put("header_title", header_title);

				JSONObject hLinkObj = new JSONObject();
				hLinkObj.put("web_url", web_url);
				hLinkObj.put("mobile_web_url", mobile_web_url);
				hLinkObj.put("android_execution_params", android_execution_params);
				hLinkObj.put("ios_execution_params", ios_execution_params);
				obj.put("header_link", hLinkObj);

				LinkedList<JSONObject> ct = new LinkedList<JSONObject>();
				JSONObject contentObj = new JSONObject();
				contentObj.put("title", "자전거 라이더를 위한 공간");
				contentObj.put("description", "매거진");
				contentObj.put("image_url",
						"http://mud-kage.kakao.co.kr/dn/QNvGY/btqfD0SKT9m/k4KUlb1m0dKPHxGV8WbIK1/openlink_640x640s.jpg");
				contentObj.put("image_width", 640);
				contentObj.put("image_height", 640);

				JSONObject linkObj = new JSONObject();
				linkObj.put("web_url", web_url);
				linkObj.put("mobile_web_url", mobile_web_url);
				linkObj.put("android_execution_params", android_execution_params);
				linkObj.put("ios_execution_params", ios_execution_params);
				contentObj.put("link", linkObj);
				ct.add(contentObj);

				obj.put("contents", ct);

				LinkedList<JSONObject> btn = new LinkedList<JSONObject>();
				JSONObject buttonObj = new JSONObject();
				buttonObj.put("title", "웹으로 이동");
				JSONObject blinkObj = new JSONObject();
				blinkObj.put("web_url", "http://www.daum.net");
				blinkObj.put("mobile_web_url", "http://www.daum.net");
				buttonObj.put("link", blinkObj);
				btn.add(buttonObj);

				obj.put("buttons", btn);

			} catch (JSONException jex) {
				System.out.println("json error occured!");
			}

			DataOutputStream output = new DataOutputStream(conn.getOutputStream());
			System.out.println("template_object=" + obj.toString());
			output.writeUTF("template_object=" + obj.toString());
			output.flush();
			output.close();

			int responseCode = conn.getResponseCode();
			String responseReason = conn.getResponseMessage();
			System.out.println("responseCode : " + responseCode);
			System.out.println("responseReason : " + responseReason);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}