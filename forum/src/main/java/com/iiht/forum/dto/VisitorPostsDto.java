package com.iiht.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorPostsDto {
	private long postId;
	private String title;
	private String tags;
	private String postDescription;
}