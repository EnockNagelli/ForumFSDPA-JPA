package com.iiht.forum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "postdata")
public class VisitorPosts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "postid")
	private Long postId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "tags")
	private String tags;
	
	@Column(name = "postDescription")
	private String postDescription;
}