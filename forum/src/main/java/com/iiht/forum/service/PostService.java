package com.iiht.forum.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorPosts;
import com.iiht.forum.repository.PostRepository;

@Service
@Transactional
public class PostService {
	
	@SuppressWarnings("unused")
	@Autowired
	private PostRepository repository; 

	public VisitorPostsDto saveUpdate(VisitorPostsDto postDtoInput) {
		return null;
	}

	public VisitorPostsDto deletePostById(Long postId) {
		return null;
	}

	public VisitorPostsDto getPostById(Long postId) {
		return null;
	}

	public List<VisitorPostsDto> getAllPosts() {
		return null;
	}

	public Map<Long, String> getAllDiscussions() {
		return null;
	}
	
	public VisitorPostsDto getVisitorPost(VisitorPosts posts) {
		return null;
	}
	
	public VisitorPostsDto getVisitorPostsDto(VisitorPosts posts) {
		return null;
	}
}