package com.iiht.forum.UtilTestClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.dto.VisitorPostsDto;

import java.io.IOException;

public class JSONUtils 
{
    public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static VisitorPostsDto createPostDto(Long postId, String title, String tags, String postDescription) {

    	VisitorPostsDto posts = new VisitorPostsDto();

    	posts.setPostId(postId);
    	posts.setTitle(title);
    	posts.setTags(tags);
    	posts.setPostDescription(postDescription);
  	 	
    	return posts;
    }
    
    public static VisitorCommentsDto createCommentDto(Long commentId, Long postId, String tags, String postComment) 
    {
    	VisitorCommentsDto comments = new VisitorCommentsDto();

    	comments.setCommentId(commentId);
    	comments.setPostId(postId);
    	comments.setTags(tags);
    	comments.setVisitorComment(postComment);
  	 	
    	return comments;
    }
}