package com.actsconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actsconnect.model.Job;

public interface JobsRepository extends JpaRepository<Job, Integer> {
	
	public List<Job> findByUserId(Integer  userId);

}
