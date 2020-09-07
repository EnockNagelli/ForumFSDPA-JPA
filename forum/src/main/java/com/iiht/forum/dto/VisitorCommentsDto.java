package com.iiht.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorCommentsDto {
	private long commentId;
	private long postId;
	private String tags;
	private String visitorComment;
}