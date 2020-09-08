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
@RequestMapping("/post")
public class VisitorPostController {

	@Autowired
	private PostService postService;

	@RequestMapping("/")
	public String home() {
		return "Welcome to Online Forum Application...";
	}

	//================================================================================================
	//			POST SERVICE - REST END POINTS - 5
	//------------------------------------------------------------------------------------------------
	@PostMapping(value = "/addPost")								 // TestCases : 2 - Exception Test : 1 
	public ResponseEntity<VisitorPostsDto> addPost(@Valid @RequestBody VisitorPostsDto visitorPostsDto, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
           throw new PostException("Invalid Visitor Post Data!");
		else
			return new ResponseEntity<VisitorPostsDto>(postService.saveUpdate(visitorPostsDto), HttpStatus.OK);
	}
	//------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/delete/{postId}")					 // TestCases : 2 - Exception Test : 1 
	public ResponseEntity<VisitorPostsDto> deleteVisitorPost(@PathVariable("postId") Long postId) 
	{
		if(postService.deletePostById(postId) == null)
			throw new PostNotFoundException("Invalid PostId!! Please enter valid postId...");
		else	
			return new ResponseEntity<VisitorPostsDto>(postService.deletePostById(postId), HttpStatus.OK);
	}	
	//------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getPostById/{postId}")				 // TestCases : 2 - Exception Test : 1
	public ResponseEntity<VisitorPostsDto> getVisitorByPostId(@PathVariable("postId") Long postId)
	{
		if(postService.getPostById(postId) == null)
			throw new PostNotFoundException("Invalid PostId!! Please enter valid postId...");
		else
			return new ResponseEntity<VisitorPostsDto>(postService.getPostById(postId), HttpStatus.OK);
	}
	//------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getAllPosts")
	public ResponseEntity<List<VisitorPostsDto>> getAllVisitorPosts()
	{
		return new ResponseEntity<List<VisitorPostsDto>>(postService.getAllPosts(), HttpStatus.OK);
	}
	//------------------------------------------------------------------------------------------------
	@GetMapping(value = "/getDiscussionList")
	public ResponseEntity<Map<Long, String>> getAllDiscussions() 
	{
		return new ResponseEntity<Map<Long, String>>(postService.getAllDiscussions(), HttpStatus.OK);
	}
	
	//================================================================================================
	//			UTITLITY EXCEPTION HANDLERS - 2
	//------------------------------------------------------------------------------------------------
	@ExceptionHandler(PostException.class)
	public ResponseEntity<PostExceptionResponse> PostHandler(PostException ex) {
		PostExceptionResponse resp = new PostExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<PostExceptionResponse> response = new ResponseEntity<PostExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	//------------------------------------------------------------------------------------------------
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<PostExceptionResponse> PostHandler(PostNotFoundException ex){
		PostExceptionResponse resp = new PostExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		ResponseEntity<PostExceptionResponse> response = new ResponseEntity<PostExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}		
}