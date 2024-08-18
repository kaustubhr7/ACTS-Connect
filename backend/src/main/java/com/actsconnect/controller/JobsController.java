package com.actsconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actsconnect.dto.JobsDto;
import com.actsconnect.dto.mapper.JobsDtoMapper;
import com.actsconnect.exception.UserException;
import com.actsconnect.model.Job;
import com.actsconnect.model.User;
import com.actsconnect.service.JobsService;
import com.actsconnect.service.UserService;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<JobsDto> createJob(@RequestBody Job job,
																						 @RequestHeader("Authorization") String jwt) throws UserException{
		User user =userService.findUserProfileByJwt(jwt);
		Job newJob = jobsService.createJob(job, user);
		
		JobsDto jobsDto = JobsDtoMapper.toJobsDto(newJob);
		
		return new ResponseEntity<>(jobsDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<JobsDto>> getAllJobs(
			@RequestHeader("Authorization") String jwt) throws UserException{
		User user =userService.findUserProfileByJwt(jwt);
		List<Job> jobs= jobsService.findAllJobs();
		
		List<JobsDto> jobsDtos = JobsDtoMapper.toJobsDtos(jobs);
		
		return new ResponseEntity<>(jobsDtos,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<JobsDto>> getUsersJobs(
			@RequestHeader("Authorization") String jwt,@PathVariable Integer userId) throws UserException{
		User user =userService.findUserProfileByJwt(jwt);
		List<Job> jobs= jobsService.findPostedJobs(userId);
		
		List<JobsDto> jobsDtos = JobsDtoMapper.toJobsDtos(jobs);
		
		return new ResponseEntity<>(jobsDtos,HttpStatus.ACCEPTED);
	}
}
