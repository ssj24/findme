package com.ssafy.findme.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FriendDTO {
	private String profile_nickname;
	private String profile_thumbnail_image;
	private int id;
	private String uuid;
	private boolean favorite;
	private List<String> uuids;
	
	public FriendDTO(String profile_nickname, String profile_thumbnail_image, int id, String uuid, boolean favorite) {
		super();
		this.profile_nickname = profile_nickname;
		this.profile_thumbnail_image = profile_thumbnail_image;
		this.id = id;
		this.uuid = uuid;
		this.favorite = favorite;
	}

}
