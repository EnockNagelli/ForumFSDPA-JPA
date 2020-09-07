package com.iiht.forum.dto;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorPostsDto 
{
	private long postId;

	@Length(min=3, max=50)
	@Column(name = "title")
	private String title;

	@Length(min=3, max=50)
	@Column(name = "tags")
	private String tags;

	@Length(min=3, max=255)
	@Column(name = "postDescription")
	private String postDescription;
}