package com.ssafy.findme.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.Pick;
import com.ssafy.findme.domain.Recruit;
import com.ssafy.findme.domain.User;
import com.ssafy.findme.dto.RecruitDTO;
import com.ssafy.findme.repository.AccountRepository;
import com.ssafy.findme.repository.PickRepository;
import com.ssafy.findme.repository.RecruitRepository;
import com.ssafy.findme.repository.SaraminRepository;

@Service
public class RecruitServiceImpl implements IRecruitService {
	@Autowired
	private RecruitRepository recruitRepo;

	@Autowired
	private SaraminRepository saraminRepo;

	@Autowired
	private PickRepository pickRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void itemBased() {
		while (true) {
			ServerSocket serverSocket = null;

			try {
				serverSocket = new ServerSocket(8112);
				System.out.println("접속 대기중...");
				Socket socket = serverSocket.accept();
				System.out.println("클라이언트 접속");

				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				String rev = br.readLine();
				System.out.println(rev);

				br.close();
				socket.close();
				serverSocket.close();
				System.out.println("종료");
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}
	}

	@Override
	public List<RecruitDTO> getMatchRecruit(String userId) {
		String filePath = "C:\\MatchRecruit.py";
		ProcessBuilder pb = new ProcessBuilder().command("C:\\Users\\multicampus\\Python\\Scripts\\python", filePath,
				userId);
		Process p;
		List<String> matchRecruitIdList = new ArrayList<>();
		Recruit matchRecruit = new Recruit();
		List<RecruitDTO> matchRecruitList = new ArrayList<>();

		try {
			p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			int exitCode = p.waitFor();
			String newLine = sb.toString().replace("[", "").replace("]", "").replace(" ", "");
			matchRecruitIdList = Arrays.asList(newLine.split(","));
			System.out.println(matchRecruitIdList);

			for (int i = 0; i < matchRecruitIdList.size(); i++) {
				matchRecruit = recruitRepo.findById(Long.parseLong(matchRecruitIdList.get(i)))
						.orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));
//				System.out.println(matchRecruit);
				matchRecruitList.add(modelMapper.map(matchRecruit, RecruitDTO.class));
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return matchRecruitList;
	}

	@Override
	public List<RecruitDTO> getPickRecruit(String userId) {
		List<RecruitDTO> pickRecruitList = new ArrayList<>();

		try {
			List<Pick> tmpPickRecruitList = pickRepo.findByUserId(Long.parseLong(userId));

			for (int i = 0; i < tmpPickRecruitList.size(); i++) {
				Recruit pickRecruit = recruitRepo.findById(tmpPickRecruitList.get(i).getRecruit().getId())
						.orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));
				pickRecruitList.add(modelMapper.map(pickRecruit, RecruitDTO.class));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pickRecruitList;
	}

	@Override
	public List<RecruitDTO> getRecommendRecruit(String userId) {
		String filePath = "C:\\RecommendRecruit.py";
		ProcessBuilder pb = new ProcessBuilder().command("C:\\Users\\multicampus\\Python\\Scripts\\python", filePath,
				userId);
		Process p;
		List<String> recommendRecruitIdList = new ArrayList<>();
		Recruit recommendRecruit = new Recruit();
		List<RecruitDTO> recommendRecruitList = new ArrayList<>();

		try {
			p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			int exitCode = p.waitFor();
			String newLine = sb.toString().replace("[", "").replace("]", "").replace(" ", "");
			recommendRecruitIdList = Arrays.asList(newLine.split(","));

			for (int i = 0; i < recommendRecruitIdList.size(); i++) {
				recommendRecruit = recruitRepo.findById(Long.parseLong(recommendRecruitIdList.get(i)))
						.orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));
				;
//				System.out.println(matchRecruit);
				recommendRecruitList.add(modelMapper.map(recommendRecruit, RecruitDTO.class));
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return recommendRecruitList;
	}

	@Override
	public List<String> getRecommendLanguage(String userId, List<RecruitDTO> matchRecruitList) {
		String[] LanguageList = { "Java", "Python", "C", "C++", "C#", "Visual Basic .NET", "JavaScript", "PHP", "SQL",
				"Go", "R", "Assembly", "Swift", "Ruby", "MATLAB", "PL/SQL", "Perl", "Visual Basic", "Objective-C",
				"Delphi/Object" };

		System.out.println(userId);

		User myInfo = accountRepo.findById(Long.parseLong(userId))
				.orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));
		String myTechStack = myInfo.getTechStack();
		List<String> myTechStackList = Arrays.asList(myTechStack.split(","));
		int[] countMatchTechStack = new int[20];

		System.out.println("내 기술 스택 : " + myTechStackList);

		for (int i = 0; i < matchRecruitList.size() / 2; i++) {
			List<String> matchRecruitTechStack = Arrays
					.asList(matchRecruitList.get(i).getTechStack().replace("·", ",").split(","));

			System.out.println("맞춤 기술 스택: " + matchRecruitTechStack);

			for (int j = 0; j < LanguageList.length; j++) {
				for (int k = 0; k < matchRecruitTechStack.size(); k++) {
					String language = LanguageList[j] == "JavaScript" ? "자바스크립트" : LanguageList[j];
					if (matchRecruitTechStack.get(k).contains(language)) {
						countMatchTechStack[j]++;
						break;
					}
				}
			}
		}
		List<String> recommendLanguageList = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			if (countMatchTechStack[i] > 10) {
				recommendLanguageList.add(LanguageList[i]);
			}
		}

		for (int j = 0; j < myTechStackList.size(); j++) {
			if (recommendLanguageList.contains(myTechStackList.get(j).trim())) {
				recommendLanguageList.remove(myTechStackList.get(j).trim());
			}
		}
		return recommendLanguageList;
	}

	@Scheduled(cron = "0 0 0/1 * * *") // 매일 0시부터 1시간마다 수행
	public void deleteRecruit() {
		System.out.println("scheduleDeleteRecruit: " + new Date());
		// 마감일이 지금보다 이르면 다 지워주자
		long now = (Calendar.getInstance().getTimeInMillis() / 1000);
		recruitRepo.deleteByDueDateBefore(now);
		saraminRepo.deleteByDueDateBefore(now);

		System.out.println("End DeleteRecruit");
	}

	@Scheduled(cron = "0 5 4 * * *") // 매일 오전 4시 수행
	public void updateRecruit() {
		// 실행
		System.out.println("scheduleSaramin & textMining: " + new Date());
		// recruit table에 있는 id중 가장 큰 값을 가져와서
		int max_id = (int) (long) recruitRepo.findMaxId();
		CommandLineExecutor.execute("python src/main/python/saramin.py " + max_id);
		CommandLineExecutor.execute("python src/main/python/textmining.py");
		System.out.println("End Saramin & textMining");
	}
}
