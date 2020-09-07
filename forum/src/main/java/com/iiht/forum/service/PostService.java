package com.iiht.forum.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorPosts;
import com.iiht.forum.repository.PostRepository;

@Service
@Transactional
public class PostService {
	
	@Autowired
	private PostRepository repository; 
	//--------------------------------------------------------------------------------------------------
	public VisitorPostsDto saveUpdate(VisitorPostsDto postDtoInput)
	{
		VisitorPosts posts = new VisitorPosts();
		
		posts.setPostId(postDtoInput.getPostId());
		posts.setTitle(postDtoInput.getTitle());
		posts.setTags(postDtoInput.getTags());
		posts.setPostDescription(postDtoInput.getPostDescription());
		
		return getVisitorPost(repository.save(posts));
	}
	//--------------------------------------------------------------------------------------------------
	public VisitorPostsDto deletePostById(Long postId)
	{
		VisitorPostsDto postDeleted = getPostById(postId);
		repository.deleteById(postId);
		return postDeleted;
	}
	//--------------------------------------------------------------------------------------------------
	public VisitorPostsDto getPostById(Long postId)
	{
		return getVisitorPost(repository.findPostById(postId));
	}
	//--------------------------------------------------------------------------------------------------
	public List<VisitorPostsDto> getAllPosts()
	{
		List<VisitorPosts> posts = repository.findAllPosts();
		
		if(CollectionUtils.isEmpty(posts))
			throw new RuntimeException("NO records found in the Database...");
		else
			return posts.stream().map(this::getVisitorPostsDto).collect(Collectors.toList());
	}
	//--------------------------------------------------------------------------------------------------
	public Map<Long, String> getAllDiscussions() {
		HashMap<Long, String> discussion = new HashMap<Long, String>();
		try	{
			discussion = new HashMap<Long, String>();
			List<VisitorPostsDto> posts = getAllPosts();
			if(!CollectionUtils.isEmpty(posts)) {
				for(VisitorPostsDto p : posts) {
					discussion.put(p.getPostId(), p.getTitle());
				}
			}
			else {
				throw new RuntimeException("No Records Found");
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return discussion;
	}
	
	//--------------------------------------------------------------------------------------------------
	//			# Local Support for conversions
	//--------------------------------------------------------------------------------------------------
	public VisitorPostsDto getVisitorPost(VisitorPosts posts) {
		return new VisitorPostsDto(posts.getPostId(), posts.getTitle(), posts.getTags(), posts.getPostDescription());
	}
	
	public VisitorPostsDto getVisitorPostsDto(VisitorPosts posts) {
		return new VisitorPostsDto(posts.getPostId(), posts.getTitle(), posts.getTags(), posts.getPostDescription());
	}
}
