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
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorPosts;
import com.iiht.forum.repository.PostRepository;
import com.iiht.forum.service.PostService;

@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
public class TestPostRepository {

    @Mock
    private PostRepository repository;

    @InjectMocks
    private PostService	postService;
    
    @Test
    public void testPostRepository() throws Exception
    {
		VisitorPosts vc = MasterData.getPostDetails();
		
		Mockito.when(repository.save(vc)).thenReturn(vc);
				
		//Mockito.when(repository.save(any(VisitorComments.class))).thenReturn(new VisitorComments());

		VisitorPostsDto posts = postService.getPostById((long)101);
        
	    yakshaAssert(currentTest(), (posts != null ? true : false), businessTestFile);	    
    }
}