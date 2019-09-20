package com.arizal.api;

import com.arizal.api.entity.User;
import com.arizal.api.model.UserServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiArizalApplicationTests {

    @Autowired
    private UserServices userServices;

	@Test
	public void contextLoads() {
	    try {
            User user = new User();
            user.setName("Mobile App 1");
            user.setAuthorities("MOBILE");
            user.setPassword("m123");
            user.setUsername("M001");
            userServices.insert(user);

            user = new User();
            user.setName("Web App 1");
            user.setAuthorities("WEB");
            user.setPassword("w123");
            user.setUsername("W001");
            userServices.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
