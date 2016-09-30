package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicApplicationTests {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @Test
    public void contextLoaded() throws Throwable {
        RestTemplate restTemplate = this.restTemplateBuilder
                .basicAuthorization("pwebb", "boot").build();
        ResponseEntity<String> response = restTemplate
                .getForEntity("http://localhost:" + this.port + "/hi", String.class);
        assertThat(response.getBody()).containsIgnoringCase("hello, pwebb");
        System.out.println("received: " + response.getBody());

    }

}
