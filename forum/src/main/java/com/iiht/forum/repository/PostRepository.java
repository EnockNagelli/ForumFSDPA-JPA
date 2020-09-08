package com.iiht.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iiht.forum.model.VisitorPosts;

public interface PostRepository extends JpaRepository<VisitorPosts, Long>
{
	@Query("select vp FROM VisitorPosts vp")
	public List<VisitorPosts> findAllPosts();
	
	@Query("select vp FROM VisitorPosts vp WHERE vp.postId=?1")
	public VisitorPosts findPostById(Long postId);
}