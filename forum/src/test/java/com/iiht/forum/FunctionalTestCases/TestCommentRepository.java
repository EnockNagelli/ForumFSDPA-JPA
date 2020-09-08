package com.iiht.forum.FunctionalTestCases;

import static com.iiht.forum.UtilTestClass.TestUtils.businessTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.iiht.forum.UtilTestClass.MasterData;
import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.repository.CommentRepository;
import com.iiht.forum.service.CommentService;

@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
public class TestCommentRepository {

    @Mock
    private CommentRepository repository;
        
    @InjectMocks
    private CommentService	commentService;

	@Test
    public void testCommentRepository() throws Exception
    {
		VisitorComments vc = MasterData.getCommentDetails();
		Mockito.when(repository.save(vc)).thenReturn(vc);
		//Mockito.when(repository.save(any(VisitorComments.class))).thenReturn(new VisitorComments());
        VisitorCommentsDto posts = commentService.getCommentById((long)101);      
	    yakshaAssert(currentTest(), (posts != null ? true : false), businessTestFile);	    
    }
}