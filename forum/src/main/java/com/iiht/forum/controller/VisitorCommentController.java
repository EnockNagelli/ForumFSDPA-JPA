package com.iiht.forum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forum.dto.CommentExceptionResponse;
import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.exception.CommentException;
import com.iiht.forum.exception.CommentNotFoundException;
import com.iiht.forum.service.CommentService;

@RestController
@RequestMapping(value="/comment")
public class VisitorCommentController 
{
	@SuppressWarnings("unused")
	@Autowired
	private CommentService commentService;

	@PostMapping(value="/addComment")
	public ResponseEntity<VisitorCommentsDto> saveUpdate(@Valid @RequestBody VisitorCommentsDto visitorCommentsDto, BindingResult bindingResult) {
		return null;
	}

	@DeleteMapping(value = "/delete/{postId}")
	public ResponseEntity<VisitorCommentsDto> deleteVisitorComment(@PathVariable("postId") Long postId) {
		return null;
	}	
	//------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getCommentById/{postId}")
	public ResponseEntity<VisitorCommentsDto> getVisitorByCommentId(@PathVariable Long postId) {
		return null;
	}
	//------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getAllComments")
	public ResponseEntity<List<VisitorCommentsDto>> getAllVisitorComments() {
		return null;
	}
	
	//------------------------------------------------------------------------------------------------
	//			UTITLITY EXCEPTION HANDLERS - 2
	//------------------------------------------------------------------------------------------------
	@ExceptionHandler(CommentException.class)
	public ResponseEntity<CommentExceptionResponse> commentHandler(CommentException ex) {
		CommentExceptionResponse resp = new CommentExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<CommentExceptionResponse> response =	new ResponseEntity<CommentExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	//------------------------------------------------------------------------------------------------
	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<CommentExceptionResponse> commentHandler(CommentNotFoundException ex){
		CommentExceptionResponse resp = new CommentExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		ResponseEntity<CommentExceptionResponse> response = new ResponseEntity<CommentExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}	
}