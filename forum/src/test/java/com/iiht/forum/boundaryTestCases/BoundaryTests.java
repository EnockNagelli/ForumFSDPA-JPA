/*
 * Boundary Tests : To Test for validation of each field member of given model
 */
package com.iiht.forum.boundaryTestCases;

import static com.iiht.forum.UtilTestClass.TestUtils.boundaryTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iiht.forum.UtilTestClass.MasterData;
import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.dto.VisitorPostsDto;

// As many validation as constraints on each field
public class BoundaryTests {

	@Autowired
	private Validator validator;
	
	//---------------------------------------------------------------------------------------------
	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	//---------------------------------------------------------------------------------------------
	//			1. VisitorPostsDto - Properties length validation
	//---------------------------------------------------------------------------------------------
	@Test
	public void testPostTitleLength() throws Exception {
		VisitorPostsDto post = MasterData.getPostDtoDetails();
		post.setTitle("God");
		Set<ConstraintViolation<VisitorPostsDto>> violations = validator.validate(post);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false, boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testPostTagsLength() throws Exception {
		VisitorPostsDto post = MasterData.getPostDtoDetails();
		post.setTags("Dev");
		Set<ConstraintViolation<VisitorPostsDto>> violations = validator.validate(post);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------
	@Test
	public void testPostDescriptionLength() throws Exception {
		VisitorPostsDto post = MasterData.getPostDtoDetails();
		post.setPostDescription("Test");
		Set<ConstraintViolation<VisitorPostsDto>> violations = validator.validate(post);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);		
	}
	//---------------------------------------------------------------------------------------------
	//			2. VisitorCommentsDto - Properties length validation
	//---------------------------------------------------------------------------------------------
	@Test
	public void testCommentTagsLength() throws Exception {
		VisitorCommentsDto comment = MasterData.getCommentDtoDetails();
		comment.setTags("Dev");
		Set<ConstraintViolation<VisitorCommentsDto>> violations = validator.validate(comment);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
	}
	@Test
	public void testVisitorCommentsLength() throws Exception {
		VisitorCommentsDto comment = MasterData.getCommentDtoDetails();
		comment.setVisitorComment("Dev");
		Set<ConstraintViolation<VisitorCommentsDto>> violations = validator.validate(comment);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
	}
	//---------------------------------------------------------------------------------------------
	//			3. VisitorPost validation - Success or Failure
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostSuccess() throws IOException {
    	VisitorPostsDto posts = MasterData.getPostDtoDetails();
        Set<ConstraintViolation<VisitorPostsDto>> violations = validator.validate(posts);
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);	    
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostFailed() throws IOException {
    	VisitorPostsDto posts = MasterData.getPostDtoDetails();
    	posts.setPostDescription(null);
        Set<ConstraintViolation<VisitorPostsDto>> violations = validator.validate(posts);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }
	//---------------------------------------------------------------------------------------------
	//			4. VisitorComment validation - Success or Failure
    //----------------------------------------------------------------------------------------------
    @Test
    public void testCommentSuccess() throws IOException {
    	VisitorCommentsDto comments = MasterData.getCommentDtoDetails();
        Set<ConstraintViolation<VisitorCommentsDto>> violations = validator.validate(comments);
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, boundaryTestFile);
    }
    //----------------------------------------------------------------------------------------------
    @Test
    public void testCommentFailed() throws IOException {
    	VisitorCommentsDto comments = MasterData.getCommentDtoDetails();
    	comments.setVisitorComment(null);
    	Set<ConstraintViolation<VisitorCommentsDto>> violations = validator.validate(comments);
	    yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }
}