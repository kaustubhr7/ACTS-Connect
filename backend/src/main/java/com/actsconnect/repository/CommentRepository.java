package com.actsconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actsconnect.model.Comments;


public interface CommentRepository extends JpaRepository<Comments, Integer> {

}
