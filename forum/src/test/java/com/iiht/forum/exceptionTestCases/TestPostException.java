/*
 * Test Description : Exception Test cases for BAD Posts with respect to JSON compatibility
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
import com.iiht.forum.controller.VisitorPostController;
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.service.PostService;

@WebMvcTest(VisitorPostController.class)
@RunWith(SpringRunner.class)
public class TestPostException 
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PostService postService;

	//--------------------------------------------------------------------------------------------
	@Test
	public void testSavePostException() throws Exception
	{
		VisitorPostsDto postDto = MasterData.getPostDtoDetails();
		postDto.setTitle(null);
		
		Mockito.when(postService.saveUpdate(postDto)).thenReturn(postDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/post/addPost")
				.content(MasterData.asJsonString(postDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);	
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 400 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------
	@Test
	public void testPostNotFoundException() throws Exception
	{
		VisitorPostsDto postDto = MasterData.getPostDtoDetails();

		Mockito.when(postService.getPostById(null)).thenReturn(postDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/post/getPostById"+null)
				.content(MasterData.asJsonString(postDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}
	//--------------------------------------------------------------------------------------------
	@Test
	public void testPostNotFoundException1() throws Exception
	{
		VisitorPostsDto postDto = MasterData.getPostDtoDetails();

		Mockito.when(postService.deletePostById(null)).thenReturn(postDto);
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/post/delete/"+null)
				.content(MasterData.asJsonString(postDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 404 ? true : false, exceptionTestFile);		
	}	
}





//	yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
