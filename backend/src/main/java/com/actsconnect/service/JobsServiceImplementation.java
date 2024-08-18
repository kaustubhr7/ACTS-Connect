package com.actsconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actsconnect.exception.UserException;
import com.actsconnect.model.Job;
import com.actsconnect.model.User;
import com.actsconnect.repository.JobsRepository;

@Service
public class JobsServiceImplementation implements JobsService {
	
	@Autowired
	private JobsRepository jobsRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Job createJob(Job job, User user) {
		Job createdJob = new Job();
		
		createdJob.setTitle(job.getTitle());
		createdJob.setUser(user);
		createdJob.setDescription(job.getDescription());
		createdJob.setCompany(job.getCompany());
		createdJob.setLocation(job.getLocation());
		
		return jobsRepository.save(createdJob);
	}

	@Override
	public List<Job> findAllJobs() {
		
		return jobsRepository.findAll();
	}

	@Override
	public List<Job> findPostedJobs(Integer userId) throws UserException {
		userService.findUserById(userId);
		return jobsRepository.findByUserId(userId);
	}

  @Override
  public List<Job> findAppliedJobs(Integer userId) throws UserException {
    return null;
  }

}
