/*
 * Test Description : Exception Test cases for BAD Post Comments with respect to JSON compatibility
 */
package com.iiht.forum.exceptionTestCases;

import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.exceptionTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.forum.UtilTestClass.MasterData;
import com.iiht.forum.controller.VisitorCommentController;
import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.service.CommentService;

@WebMvcTest(VisitorCommentController.class)
@RunWith(SpringRunner.class)
public class TestCommentException 
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CommentService commentService;

	//--------------------------------------------------------------------------------------------
	@Test
	public void testSaveCommentException() throws Exception
	{
		VisitorCommentsDto commentDto = MasterData.getCommentDtoDetails();
		commentDto.setTags(null);	
		Mockito.when(commentService.saveUpdate(commentDto)).thenReturn(commentDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/comment/addComment")
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 400 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------
	@Test
	public void testCommentNotFoundException() throws Exception
	{
		VisitorCommentsDto commentDto = MasterData.getCommentDtoDetails();

		Mockito.when(commentService.getCommentById(null)).thenReturn(commentDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/comment/getCommentById/"+null)
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------
	@Test
	public void testCommentNotFoundException1() throws Exception
	{
		VisitorCommentsDto commentDto = MasterData.getCommentDtoDetails();

		Mockito.when(commentService.deleteCommentById(null)).thenReturn(commentDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/comment/delete/"+null)
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}	
}





//	yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
