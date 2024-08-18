package com.actsconnect.service;

import java.util.List;

import com.actsconnect.exception.UserException;
import com.actsconnect.model.Job;
import com.actsconnect.model.User;

public interface JobsService {
	
	public Job createJob(Job job, User user);
	public List<Job> findAllJobs();
	public List<Job> findPostedJobs(Integer userId) throws UserException;
	public List<Job> findAppliedJobs(Integer userId) throws UserException;
}
