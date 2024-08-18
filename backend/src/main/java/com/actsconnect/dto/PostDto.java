package com.actsconnect.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PostDto {
	

	private Integer id;
	
	private String caption;
	
	private String image;

	private LocalDateTime createdAt;
	
	private UserDto user;
	
	private List<CommentDto> comments=new ArrayList<>();
	
	private List<UserDto> liked= new ArrayList<>(); 
	
	private boolean likedByRequser;
	
	private boolean savedByRequser;
	

}
