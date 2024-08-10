package com.aj.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aj.blog.repositories.UserRepository;

@SpringBootTest
class BlogApplicationTests {
	
	@Autowired
	private UserRepository repository;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testRepo()
	{
		String className = this.repository.getClass().getName();
		String packageName = this.repository.getClass().getPackage().getName();
		
		System.out.println(className);
		System.out.println(packageName);

	}

}
