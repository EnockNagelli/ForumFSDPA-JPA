package com.iiht.forum.UtilTestClass;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.model.VisitorPosts;

public class MasterData
{
	//-------------------------------------------------------------------------------------
	//		1. VisitorPosts
	//-------------------------------------------------------------------------------------
	public static VisitorPosts getPostDetails() 
	{
		VisitorPosts post = new VisitorPosts();
		
		post.setPostId((long) 101);
		post.setTitle("Spring Technology");
		post.setTags("Java");
		post.setPostDescription("Used in IT Sector");
		
		return post;
	}
	
	public static VisitorPostsDto getPostDtoDetails() 
	{
		VisitorPostsDto post = new VisitorPostsDto();
		
		post.setPostId(201);
		post.setTitle("Spring Technology");
		post.setTags("Java");
		post.setPostDescription("Used in IT Sector");
		
		return post;
	}
	//-------------------------------------------------------------------------------------
	//		2. VisitorComments
	//-------------------------------------------------------------------------------------
	public static VisitorComments getCommentDetails() 
	{
		VisitorComments comments = new VisitorComments();
		
		comments.setCommentId((long)101);
		comments.setPostId((long)101);
		comments.setTags("I Like it");
		comments.setVisitorComment("Design Patterns are important in Java Technology");
		
		return comments;
	}
	
	public static VisitorCommentsDto getCommentDtoDetails() 
	{
		VisitorCommentsDto comments = new VisitorCommentsDto();
		
		comments.setCommentId(201);
		comments.setPostId(201);
		comments.setTags("I Like it");
		comments.setVisitorComment("Design Patterns are important in Java Technology");
		
		return comments;
	}

	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}