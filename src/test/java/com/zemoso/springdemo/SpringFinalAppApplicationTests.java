package com.zemoso.springdemo;

import com.zemoso.springdemo.controller.BlogAdminController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringFinalAppApplicationTests {

	@Autowired
	BlogAdminController blogAdminController;

	@Test
	void contextLoads() {
		Assertions.assertThat(blogAdminController).isNotNull();
	}

}
