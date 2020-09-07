package com.iiht.forum.FunctionalTestCases;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.iiht.forum.model.VisitorPosts;
import com.iiht.forum.repository.PostRepository;

import static com.iiht.forum.UtilTestClass.TestUtils.businessTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;

@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
public class TestPostRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository repository;

    @Test
    public void testFindByPostId() throws Exception
    {
        entityManager.persist(new VisitorPosts((long)10, "Cooking", "Food", "Hyderabad Biryani is very famous."));
        entityManager.persist(new VisitorPosts((long)11, "Computers", "Technology", "Used to compute for the given inputs."));

        VisitorPosts posts = repository.findPostById((long)10);
        
	    yakshaAssert(currentTest(), (posts != null ? true : false), businessTestFile);	    
    }
}