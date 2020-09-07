package com.iiht.forum.FunctionalTestCases;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.repository.CommentRepository;

import static com.iiht.forum.UtilTestClass.TestUtils.businessTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;

@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
public class TestCommentRepository {

    @Autowired
    private CommentRepository repository;

	@Test
    public void testFindByPostId() throws Exception
    {
		repository.save(new VisitorComments((long)51, (long)10, "Food", "I like Hyderabad Biryani"));
    	repository.save(new VisitorComments((long)52, (long)11, "Technology", "I used laptop"));

        VisitorComments posts = (VisitorComments) repository.findVisitorById((long)10);
               
	    yakshaAssert(currentTest(), (posts != null ? true : false), businessTestFile);	    
    }
}