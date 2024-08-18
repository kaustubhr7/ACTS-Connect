package com.actsconnect.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String mobile;
	private String website;
	private String bio;
	private String gender;
	private String image;
	
	@JsonIgnore
	@ManyToMany
	private Set<User> follower = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "follower",fetch = FetchType.LAZY)
	private Set<User> following = new HashSet<User>();

	@JsonIgnore
	@ManyToMany
	private List<Post> savedPosts = new ArrayList<>();
	
	@JsonManagedReference
	@JsonIgnore
	@ManyToMany
	private List<Post> reposts = new ArrayList<>();
	
	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Notification> notifications=new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Job> jobs = new ArrayList<>();

}
