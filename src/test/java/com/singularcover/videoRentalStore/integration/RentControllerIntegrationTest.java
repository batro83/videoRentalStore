package com.singularcover.videoRentalStore.integration;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentControllerIntegrationTest {
	

    @Autowired
    private TestRestTemplate restTemplate;
    
    
    @Test
    public void test4_moveBackwardTest() {
//    	final String path = "/mars/{command}";
//    	final ResponseEntity<String> response = post("b", path);
//    	 Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
//         Assert.assertEquals("(1,1,N)", response.getBody());
	}
	
	private ResponseEntity<String> post(String command, String path) {
        return restTemplate.exchange(path, HttpMethod.POST, HttpEntity.EMPTY, String.class, command);
    }

}
