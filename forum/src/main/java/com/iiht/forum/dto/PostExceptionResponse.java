package com.iiht.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostExceptionResponse {
	private String message;
	private long timeStamp;
	private int status;
}