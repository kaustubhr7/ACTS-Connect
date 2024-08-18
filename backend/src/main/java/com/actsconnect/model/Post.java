package com.actsconnect.model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String caption;
	
	private String image;
	
	private String video;
	
	private String location;
	
	private LocalDateTime createdAt;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private List<Comments> comments=new ArrayList<Comments>();
	
	@ManyToMany
	private Set<User> liked= new HashSet<>(); 

	

	
	
}
