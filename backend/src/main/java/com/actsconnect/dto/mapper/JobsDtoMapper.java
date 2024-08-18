package com.actsconnect.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.actsconnect.dto.JobsDto;
import com.actsconnect.dto.UserDto;
import com.actsconnect.model.Job;

public class JobsDtoMapper {
	
	
	public static JobsDto toJobsDto(Job job) {
		JobsDto jobsDto =new JobsDto();
		
		UserDto user=UserDtoMapper.userDTO(job.getUser());
		
		jobsDto.setTitle(job.getTitle());
		jobsDto.setCompany(job.getCompany());
		jobsDto.setDescription(job.getDescription());
		jobsDto.setLocation(job.getLocation());
		
		return jobsDto;
		
	}
	
	public static List<JobsDto> toJobsDtos(List<Job> jobs){
		
		List<JobsDto> jobsDtos =new ArrayList<>();
		
		for(Job reel : jobs ) {
			 JobsDto jobsDto = toJobsDto(reel);
			 jobsDtos.add(jobsDto);
		}
		
		return jobsDtos;
		
	}

}
