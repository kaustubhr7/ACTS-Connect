package com.actsconnect.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CommentDto {
	

	private Integer id;
	
	private String content;
	
	private UserDto user;

	private List<UserDto> liked= new ArrayList<>();
	
	private LocalDateTime createdAt;

}
