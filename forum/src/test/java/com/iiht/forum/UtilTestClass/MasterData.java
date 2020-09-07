package com.iiht.forum.UtilTestClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.model.VisitorPosts;

public class MasterData
{
    //@Autowired
    //private static TestEntityManager entityManager;
	
	//-------------------------------------------------------------------------------------
	//		1. VisitorPosts
	//-------------------------------------------------------------------------------------
	public static VisitorPosts getPostDetails() 
	{
		VisitorPosts post = new VisitorPosts();
		
		post.setPostId((long) 101);
		post.setTitle("Spring Technology");
		post.setTags("Java");
		post.setPostDescription("Used in IT Sector");
		
		return post;
	}
	
	public static VisitorPostsDto getPostDtoDetails() 
	{
		VisitorPostsDto post = new VisitorPostsDto();
		
		post.setPostId(201);
		post.setTitle("Spring Technology");
		post.setTags("Java");
		post.setPostDescription("Used in IT Sector");
		
		return post;
	}
	//-------------------------------------------------------------------------------------
	//		2. VisitorComments
	//-------------------------------------------------------------------------------------
	public static VisitorComments getCommentDetails() 
	{
		VisitorComments comments = new VisitorComments();
		
		comments.setCommentId((long)101);
		comments.setPostId((long)101);
		comments.setTags("I Like it");
		comments.setVisitorComment("Design Patterns are important in Java Technology");
		
		return comments;
	}
	
	public static VisitorCommentsDto getCommentDtoDetails() 
	{
		VisitorCommentsDto comments = new VisitorCommentsDto();
		
		comments.setCommentId(201);
		comments.setPostId(201);
		comments.setTags("I Like it");
		comments.setVisitorComment("Design Patterns are important in Java Technology");
		
		return comments;
	}

	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
/*	//-------------------------------------------------------------------------------------
	//		3. Properties + JsonToString + LocalSessionFactoryBean
	//-------------------------------------------------------------------------------------
	public static Properties getProperties() throws IOException 
	{
		FileReader reader = new FileReader("src\\main\\resources\\application.properties");
		Properties properties = new Properties();
		properties.load(reader);
		return properties;
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static LocalSessionFactoryBean getSession() throws IOException 
	{
		Properties properties = getProperties();

		Session session = entityManager.getEntityManager().unwrap(Session.class);

		LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) session.getSessionFactory();

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(properties.getProperty("spring.datasource.url"));
		dataSource.setUsername(properties.getProperty("spring.datasource.username"));
		dataSource.setPassword(properties.getProperty("spring.datasource.password"));

		lsfb.setDataSource(dataSource);
		lsfb.setPackagesToScan("com.iiht.forum.model");

		properties.put("hibernate.dialect", properties.getProperty("spring.jpa.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", properties.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show_sql", properties.getProperty("spring.jpa.show-sql"));

		lsfb.setHibernateProperties(properties);

		try {
			lsfb.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lsfb;
	}*/
}