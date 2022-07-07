package com.recipe.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RecipeApiApplicationTests {

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	protected ObjectMapper objectMapper;

	protected MockMvc mvc(){
        return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	@Test
	void contextLoads() {
	}

}
