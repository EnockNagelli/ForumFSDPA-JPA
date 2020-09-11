package com.iiht.forum.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.repository.CommentRepository;

@Service
@Transactional
public class CommentService 
{
	@SuppressWarnings("unused")
	@Autowired
	private CommentRepository commentRepo; 
	
	public VisitorCommentsDto saveUpdate(VisitorCommentsDto commentDtoInput) {
		return null; 
	}

	public VisitorCommentsDto deleteCommentById(Long postId) {
		return null;
	}

	public VisitorCommentsDto getById(Long postId) {
		return null;
	}

	public VisitorCommentsDto getCommentById(Long postId) {
		return null;
	}

	public List<VisitorCommentsDto> getAllComments() {
		return null;
	}	

	public VisitorCommentsDto getVisitorCommentsDto(VisitorComments comments) {
		return null;
	}	
}