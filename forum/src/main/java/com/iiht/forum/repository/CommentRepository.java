package com.iiht.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iiht.forum.model.VisitorComments;

public interface CommentRepository extends JpaRepository<VisitorComments, Long>
{
	@Query("select vp FROM VisitorComments vp")
	public List<VisitorComments> findAllComments();
	
	@Query("select vp FROM VisitorComments vp WHERE vp.postId=?1")
	public VisitorComments findVisitorById(Long postId);
}