// FORUM Application for FSD PA - JPA Repository
package com.iiht.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumApplication {	
	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}
}






//@SpringBootApplication(scanBasePackages ="com.iiht.forum")
//@EnableJpaRepositories(basePackages ="com.iiht.forum.repository")
//@EntityScan("com.iiht.forum.model")


/*
 * @Bean public RestTemplate getRestTemplate() { return new RestTemplate(); }
 * 
 * @Bean public ModelMapper modelMapper() { return new ModelMapper(); }
 */