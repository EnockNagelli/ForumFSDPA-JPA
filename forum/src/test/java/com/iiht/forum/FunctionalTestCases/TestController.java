/*
 * Test Description : Test Controllers - VisitorPostController and VisitorCommentController
 */
package com.iiht.forum.FunctionalTestCases;

import static com.iiht.forum.UtilTestClass.TestUtils.businessTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.iiht.forum.UtilTestClass.JSONUtils;
import com.iiht.forum.UtilTestClass.MasterData;
import com.iiht.forum.controller.VisitorCommentController;
import com.iiht.forum.controller.VisitorPostController;
import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.service.CommentService;
import com.iiht.forum.service.PostService;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = VisitorPostController.class)
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(replace=Replace.NONE)

@WebMvcTest({ VisitorPostController.class, VisitorCommentController.class})
@RunWith(SpringRunner.class)
public class TestController 
{	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PostService postService;

	@MockBean
	private CommentService commentService;

	@InjectMocks
	private VisitorPostController visitorPostController;

	@InjectMocks
	private VisitorCommentController visitorCommentController;
	
	// --------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform setup for Mockito initiations
	 */
	@Before public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		//this.mockMvc = MockMvcBuilders.standaloneSetup(visitorPostController).build(); 
	}  
	//=======================================================================================================
	//							I - Visitor Post Test
	//=======================================================================================================
	//				1. Testing Rest End Point - /post/addPost
	//-- Test 1 : addPost -------------------------------------------------------------------
	/*
	 * Description : This test is to perform add new post in the Forum
	 */
	@Test 
	public void testAddPost() throws Exception { 
		/*
		 * VisitorPostsDto postDto = JSONUtils.createPostDto((long)4, "Foot Ball",
		 * "Field Game", "World Sport. Great Sport"); Boolean responseEntity =
		 * visitorPostController.saveUpdate(postDto, null); yakshaAssert(currentTest(),
		 * (responseEntity ? true : false), businessTestFile);
		 */
        //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        VisitorPostsDto postdto = MasterData.getPostDtoDetails();
		Mockito.when(postService.saveUpdate(postdto)).thenReturn(postdto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/post/addPost")
				.content(MasterData.asJsonString(postdto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(postdto);
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(postdto))? true : false, businessTestFile);
	}
	//-- BDD Test : addPost -------------------------------------------------------------------
	@Test
	public void testAddPostBDD() throws Exception 
	{
		final int count[] = new int[1];
		VisitorPostsDto postDto = MasterData.getPostDtoDetails();
		Mockito.when(postService.saveUpdate(postDto)).then(new Answer<VisitorPostsDto>() {
			@Override
			public VisitorPostsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return postDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/post/addPost")
				.content(MasterData.asJsonString(postDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}
		
	//---------------------------------------------------------------------------------------
	//				2. Testing Rest End Point - /post/delete/{id}
	//-- Test 1 : deletePost ----------------------------------------------------------------
	@Test
	public void testDeletePost() throws Exception
	{
		VisitorPostsDto postDto = MasterData.getPostDtoDetails();
		Long postId = postDto.getPostId();
		
		Mockito.when(postService.deletePostById(postId)).thenReturn(postDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/post/delete/" + postId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(postDto))? true : false, businessTestFile);
	}
	//-- BDD Test : deletePost ---------------------------------------------------------------
	@Test
	public void testdeletePostBDD() throws Exception 
	{
		final int count[] = new int[1];
	
		VisitorPostsDto postDto = MasterData.getPostDtoDetails();
		Long postId = postDto.getPostId();
		
		Mockito.when(postService.deletePostById(postId)).then(new Answer<VisitorPostsDto>() {
			@Override
			public VisitorPostsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return MasterData.getPostDtoDetails();
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/post/delete/" + postId)
				.content(MasterData.asJsonString(postDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);	
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

	//---------------------------------------------------------------------------------------
	//				3. Testing Rest End Point - /post/getPostById/{postId}
	//-- Test 1 : getPostById ---------------------------------------------------------------
	@Test
	public void testFindPostById() throws Exception
	{
		VisitorPostsDto postDto = MasterData.getPostDtoDetails();
		Long postId = postDto.getPostId();
		
		Mockito.when(postService.getPostById(postId)).thenReturn(postDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/post/getPostById/" + postId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(postDto))? true : false, businessTestFile);		
	}
	//-- BDD Test : getPostById -------------------------------------------------------------
	@Test
	public void testFindPostByIdBDD() throws Exception 
	{
		final int count[] = new int[1];
		
		VisitorPostsDto postDto = MasterData.getPostDtoDetails();
		Long postId = postDto.getPostId();
		
		Mockito.when(postService.getPostById(postId)).then(new Answer<VisitorPostsDto>() {
			@Override
			public VisitorPostsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return MasterData.getPostDtoDetails();
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/post/getPostById/" + postId)
				.content(MasterData.asJsonString(postDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);			
	}	
	
	//---------------------------------------------------------------------------------------
	//				4. Testing Rest End Point - /post/getAllPosts
	//-- Test 1 : getAllPosts ---------------------------------------------------------------
	/*
	 * Description : This test is to perform view all the posts from database
	 */
	@Test 
	public void testFindAllPosts() throws Exception { 
        // Given input
		/*
		 * VisitorPostsDto post1 = JSONUtils.createPostDto((long) 6, "Kabbadi",
		 * "India Sport", "Good Sport. Propularly played by all eastern countries.");
		 * VisitorPostsDto post2 = JSONUtils.createPostDto((long) 7, "Ko Ko",
		 * "India Sport", "Good Sport. Propularly played by all states in India.");
		 * VisitorPostsDto post3 = JSONUtils.createPostDto((long) 8, "Chess",
		 * "World Sport", "Great Sport. Propularly played by all countries.");
		 * List<VisitorPostsDto> list = new ArrayList<VisitorPostsDto>();
		 * list.add(post1); list.add(post2); list.add(post3); // when
		 * Mockito.when(postService.getAllPosts()).thenReturn(list);
		 * List<VisitorPostsDto> fromController = postService.getAllPosts(); // then
		 * assertThat(fromController.size()).isEqualTo(3);
		 * assertThat(fromController.get(0).getTitle()).isEqualTo(post1.getTitle());
		 * assertThat(fromController.get(0).getTags()).isEqualTo(post1.getTags());
		 * assertThat(fromController.get(0).getPostDescription()).isEqualTo(post1.
		 * getPostDescription());
		 * 
		 * assertThat(fromController.get(0).getTitle()).isEqualTo(post1.getTitle());
		 * assertThat(fromController.get(1).getTags()).isEqualTo(post2.getTags());
		 * assertThat(fromController.get(2).getPostDescription()).isEqualTo(post3.
		 * getPostDescription()); yakshaAssert(currentTest(), (fromController.size() ==
		 * 3 ? true : false), businessTestFile);
		 */
		//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		List<VisitorPostsDto> list = new ArrayList<VisitorPostsDto>();
		list.add(MasterData.getPostDtoDetails());
		
		Mockito.when(postService.getAllPosts()).thenReturn(list);
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/post/getAllPosts")
				.content(MasterData.asJsonString(MasterData.getPostDtoDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(MasterData.asJsonString(list));
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(list))? "true" : "false"),	businessTestFile);        
	}

	//-----------------------------------------------------------------------------
	/*
	 * Description : This test is to perform check the null operation against view all posts operation
	 */
	@Test 
	public void testViewAllPostsCase() throws Exception {
 		List<VisitorPostsDto> list = new ArrayList<VisitorPostsDto>();
		when(postService.getAllPosts()).thenReturn(list);
        // when
        List<VisitorPostsDto> fromController = postService.getAllPosts();
        // then
        assertThat(fromController.size()).isEqualTo(0);
		yakshaAssert(currentTest(), (fromController.size() == 0 ? true : false) ? true : false, businessTestFile); 
	}

	//---------------------------------------------------------------------------------------
	//				5. Testing Rest End Point - /post/getDiscussionList
	//-- Test 1 : getDiscussionList ---------------------------------------------------------
	/*
	 * Description : This test is to perform to view all the discussions of the posts
	 */
	@Test 
	public void testFindAllDiscussions() throws Exception { 
	    // Given input
		VisitorPostsDto post1 = JSONUtils.createPostDto((long) 6, "Kabbadi", "India Sport", "Good Sport. Propularly played by all eastern countries.");
		VisitorPostsDto post2 = JSONUtils.createPostDto((long) 7, "Ko Ko", "India Sport", "Good Sport. Propularly played by all states in India.");
		VisitorPostsDto post3 = JSONUtils.createPostDto((long) 8, "Chess", "World Sport", "Great Sport. Propularly played by all countries.");
	    List<VisitorPostsDto> list = new ArrayList<VisitorPostsDto>();
	    list.add(post1);
	    list.add(post2);
	    list.add(post3);
	    HashMap<Long, String> discussion = new HashMap<Long, String>();
		if(!CollectionUtils.isEmpty(list)) {
			for(VisitorPostsDto p : list) {
				discussion.put(p.getPostId(), p.getTitle());
			}
		}
	    // when
	    Mockito.when(postService.getAllPosts()).thenReturn(list);
	    Map<Long, String> fromController = (Map<Long, String>) visitorPostController.getAllDiscussions().getBody();
	    assertThat(fromController.size()).isEqualTo(0);
	    Set<Long> s = fromController.keySet();
	    Iterator<Long> it = s.iterator();
	    while(it.hasNext()) {
	    	System.out.println("Value : "+it.next());
	    }	    
	    yakshaAssert(currentTest(), (fromController.size() == 3 ? true : false), businessTestFile);
	}	
	
	//=======================================================================================================
	//				II - Visitor Comments Tests
	//=======================================================================================================
	//				1. Testing Rest End Point - /comment/addComment
	//-- Test 1 : addComment -------------------------------------------------------------------
	/*
	 * Description : This test is to perform add a comment and check the status of the operation
	 */
	@Test
	public void testAddComments() throws Exception {
//		VisitorCommentsDto commentDto = JSONUtils.createCommentDto((long) 4, (long) 5, "Field Game", "I like this sport");
//		Boolean responseEntity = visitorCommentController.saveUpdate(commentDto);
//      yakshaAssert(currentTest(), (responseEntity ? true : false), businessTestFile);
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	    VisitorCommentsDto commentDto = MasterData.getCommentDtoDetails();
	    
		Mockito.when(commentService.saveUpdate(commentDto)).thenReturn(commentDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/comment/addComment")
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(commentDto);
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(commentDto))? true : false, businessTestFile);
	}
	//-- BDD Test : addComment -------------------------------------------------------------
	@Test
	public void testAddCommentBDD() throws Exception 
	{
		final int count[] = new int[1];
		
		VisitorCommentsDto commentDto = MasterData.getCommentDtoDetails();
		
		Mockito.when(commentService.saveUpdate(commentDto)).then(new Answer<VisitorCommentsDto>() {
			@Override
			public VisitorCommentsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return commentDto;
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/comment/addComment")
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);	
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}
	
	//------------------------------------------------------------------------------------------
	//				2. Testing Rest End Point - /comment/delete/{postId}
	//-- Test 1 : /comment/delete --------------------------------------------------------------
	@Test
	public void testDeleteComment() throws Exception
	{
		VisitorCommentsDto commentDto = MasterData.getCommentDtoDetails();
		Long postId = commentDto.getPostId();
	
		Mockito.when(commentService.deleteCommentById(postId)).thenReturn(commentDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/comment/delete/" + postId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);				
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(commentDto))? true : false, businessTestFile);
	}
	//-- BDD Test : deleteComment --------------------------------------------------------------
	@Test
	public void testdeleteCommentBDD() throws Exception 
	{
		final int count[] = new int[1];
	
		VisitorCommentsDto commentDto = MasterData.getCommentDtoDetails();
		Long postId = commentDto.getPostId();
		
		Mockito.when(commentService.deleteCommentById(postId)).then(new Answer<VisitorCommentsDto>() {
			@Override
			public VisitorCommentsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return MasterData.getCommentDtoDetails();
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/comment/delete/" + postId)
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);	
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}	
	
	//---------------------------------------------------------------------------------------
	//				3. Testing Rest End Point - /comment/getCommentById/{postId}
	//-- Test 1 : getCommentById ---------------------------------------------------------------
	@Test
	public void testFindCommentById() throws Exception
	{
		VisitorCommentsDto commentDto = MasterData.getCommentDtoDetails();
		Long postId = commentDto.getPostId();
		
		Mockito.when(commentService.getCommentById(postId)).thenReturn(commentDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/comment/getCommentById/" + postId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),	result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(commentDto))? true : false, businessTestFile);		
	}
	//-- BDD Test : getCommentById -------------------------------------------------------------
	@Test
	public void testFindCommentByIdBDD() throws Exception 
	{
		final int count[] = new int[1];
		
		VisitorCommentsDto commentDto = MasterData.getCommentDtoDetails();
		Long postId = commentDto.getPostId();
		
		Mockito.when(commentService.getCommentById(postId)).then(new Answer<VisitorCommentsDto>() {
			@Override
			public VisitorCommentsDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Called");
				count[0]++;
				return MasterData.getCommentDtoDetails();
			}
		});
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/comment/getCommentById/" + postId)
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(count[0]);
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);			
	}	
	
	//---------------------------------------------------------------------------------------
	//				4. Testing Rest End Point - /comment/getAllComments
	//-- Test 1 : getAllComments ---------------------------------------------------------------
	/*
	 * Description : This test is to perform view all the comments from database
	 */
	@Test 
	public void testFindAllComments() throws Exception 
	{
		List<VisitorCommentsDto> list = new ArrayList<VisitorCommentsDto>();
		list.add(MasterData.getCommentDtoDetails());
		
		Mockito.when(commentService.getAllComments()).thenReturn(list);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/comment/getAllComments")
				.content(MasterData.asJsonString(MasterData.getPostDtoDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();	 
		
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(MasterData.asJsonString(list));
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(list))? "true" : "false"),	businessTestFile);        
	}
}