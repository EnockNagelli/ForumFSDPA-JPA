package com.iiht.forum.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.repository.CommentRepository;

@Service
@Transactional
public class CommentService 
{
	@Autowired
	private CommentRepository commentRepo; 
	
	//--------------------------------------------------------------------------------------------------------------------------------
	public VisitorCommentsDto saveUpdate(VisitorCommentsDto commentDtoInput)
	{
		VisitorComments comments = new VisitorComments();
		
		comments.setCommentId(commentDtoInput.getCommentId());
		comments.setPostId(commentDtoInput.getPostId());
		comments.setTags(commentDtoInput.getTags());
		comments.setVisitorComment(commentDtoInput.getVisitorComment());

		return getVisitorCommentsDto(commentRepo.save(comments)); 
	}
	//--------------------------------------------------------------------------------------------------
	public VisitorCommentsDto deleteCommentById(Long postId)
	{
		VisitorCommentsDto commentDeleted = getCommentById(postId);
		commentRepo.deleteById(postId);
		return commentDeleted;
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	public VisitorCommentsDto getById(Long postId)
	{
		return getVisitorCommentsDto(commentRepo.getOne(postId));
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	public VisitorCommentsDto getCommentById(Long postId)
	{
		return getVisitorCommentsDto(commentRepo.findVisitorById(postId));
	}
	//--------------------------------------------------------------------------------------------------
	public List<VisitorCommentsDto> getAllComments()
	{
		List<VisitorComments> posts = commentRepo.findAllComments();
		
		if(CollectionUtils.isEmpty(posts))
			throw new RuntimeException("NO records found in the Database...");
		else 
			return posts.stream().map(this::getVisitorCommentsDto).collect(Collectors.toList());
	}	

	//--------------------------------------------------------------------------------------------------
	//			# Local Support for conversions
	//--------------------------------------------------------------------------------------------------
	public VisitorCommentsDto getVisitorCommentsDto(VisitorComments comments)
	{
		return new VisitorCommentsDto(comments.getPostId(), comments.getCommentId(), comments.getTags(), comments.getVisitorComment());
	}	
}