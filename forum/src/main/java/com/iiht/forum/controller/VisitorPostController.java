package com.iiht.forum.controller;

import java.util.List;
import java.util.Map;

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

import com.iiht.forum.dto.PostExceptionResponse;
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.exception.PostException;
import com.iiht.forum.exception.PostNotFoundException;
import com.iiht.forum.service.PostService;

@RestController
@RequestMapping("/visitorPost")
public class VisitorPostController {

	@SuppressWarnings("unused")
	@Autowired
	private PostService postService;

	@RequestMapping("/home")
	public String home() {
		return "Welcome to Online Forum Application...";
	}

	@PostMapping(value = "/addPost") 
	public ResponseEntity<VisitorPostsDto> addPost(@Valid @RequestBody VisitorPostsDto visitorPostsDto, BindingResult bindingResult) {
		return null;
	}

	@DeleteMapping(value = "/delete/{postId}") 
	public ResponseEntity<VisitorPostsDto> deleteVisitorPost(@PathVariable("postId") Long postId) {
		return null;
	}

	@GetMapping(value = "/getPostById/{postId}")
	public ResponseEntity<VisitorPostsDto> getVisitorByPostId(@PathVariable("postId") Long postId) {
		return null;
	}

	@GetMapping(value = "/getAllPosts")
	public ResponseEntity<List<VisitorPostsDto>> getAllVisitorPosts() {
		return null;
	}

	@GetMapping(value = "/getDiscussionList")
	public ResponseEntity<Map<Long, String>> getAllDiscussions() {
		return null;
	}
	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<PostExceptionResponse> PostHandler(PostException ex) {
		PostExceptionResponse resp = new PostExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<PostExceptionResponse> response = new ResponseEntity<PostExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}

	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<PostExceptionResponse> PostHandler(PostNotFoundException ex) {
		PostExceptionResponse resp = new PostExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		ResponseEntity<PostExceptionResponse> response = new ResponseEntity<PostExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}		
}