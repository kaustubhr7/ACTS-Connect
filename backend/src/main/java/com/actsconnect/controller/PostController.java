package com.actsconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actsconnect.dto.PostDto;
import com.actsconnect.dto.mapper.PostDtoMapper;
import com.actsconnect.exception.PostException;
import com.actsconnect.exception.UserException;
import com.actsconnect.model.Post;
import com.actsconnect.model.User;
import com.actsconnect.response.ApiResponse;

import com.actsconnect.service.PostService;
import com.actsconnect.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<PostDto> createPostHandler(@RequestBody Post post,
			@RequestHeader("Authorization") String token) throws UserException, PostException{
		
		System.out.println("create post ---- "+post.getCaption());
		
		User user=userService.findUserProfileByJwt(token);
		
		Post createdPost = postService.createPost(post, user.getId());
		
		PostDto postDto = PostDtoMapper.toPostDto(createdPost,user);
		
		return new ResponseEntity<>(postDto, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> findPostByUserIdHandler(
			@PathVariable("userId") Integer userId,@RequestHeader("Authorization") String token) throws UserException{
		User user=userService.findUserProfileByJwt(token);
		List<Post> posts=postService.findPostByUserId(userId);
		List<PostDto> postDtos=PostDtoMapper.toPostDtos(posts,user);
		
		return new ResponseEntity<>(postDtos,HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping
	public ResponseEntity<List<PostDto>> findAllPostHandler(
			@RequestHeader("Authorization") String token) throws PostException, UserException{
		List<Post> posts=postService.findAllPost();
		User user=userService.findUserProfileByJwt(token);
		List<PostDto> postDtos=PostDtoMapper.toPostDtos(posts,user);
		
		return new ResponseEntity<>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> findPostByIdHandler(@PathVariable Integer postId,
			@RequestHeader("Authorization") String token) throws PostException, UserException{
		Post post=postService.findePostById(postId);
		User user=userService.findUserProfileByJwt(token);
		PostDto postDto = PostDtoMapper.toPostDto(post,user);
		
		return new ResponseEntity<>(postDto, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/like/{postId}")
	public ResponseEntity<PostDto> likePostHandler(
			@PathVariable("postId") Integer postId, 
			@RequestHeader("Authorization") String token) throws UserException, PostException{
		
		User user=userService.findUserProfileByJwt(token);
		
		Post updatedPost=postService.likePost(postId, user.getId());
		
		PostDto postDto = PostDtoMapper.toPostDto(updatedPost,user);
		
		return new ResponseEntity<>(postDto, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String token) throws UserException, PostException{
		
		User reqUser=userService.findUserProfileByJwt(token);
		
		String message=postService.deletePost(postId, reqUser.getId());
		
		ApiResponse res=new ApiResponse(message,true);
		
		return new ResponseEntity<> (res, HttpStatus.OK);
		
	}
	
	@PutMapping("/{postId}/save")
	public ResponseEntity<PostDto> savedPostHandler(@RequestHeader("Authorization")String token,@PathVariable Integer postId) throws UserException, PostException{
		
		User user =userService.findUserProfileByJwt(token);
		
		Post post=postService.savedPost(postId, user.getId());
		
	
	PostDto postDto = PostDtoMapper.toPostDto(post,user);
		
		return new ResponseEntity<>(postDto, HttpStatus.CREATED);
		
	}
	
	

}
