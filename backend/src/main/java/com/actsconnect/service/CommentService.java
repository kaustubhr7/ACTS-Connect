package com.actsconnect.service;

import com.actsconnect.exception.CommentException;
import com.actsconnect.exception.PostException;
import com.actsconnect.exception.UserException;
import com.actsconnect.model.Comments;

public interface CommentService {
	
	public Comments createComment(Comments comment,Integer postId,Integer userId) throws PostException, UserException;

	public Comments findCommentById(Integer commentId) throws CommentException;
	public Comments likeComment(Integer CommentId,Integer userId) 
			throws UserException, CommentException;
}
