package com.example.task;

import com.example.task.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TaskMain {
    public static void main(String[] args) {
        String url = "http://91.241.64.178:7081/api/users";

        //User 3
        User user = new User(3L, "James", "Brown", (byte) 23);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        StringBuilder result = new StringBuilder();

        //Take a cookies
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String cookies = response.getHeaders().get(HttpHeaders.SET_COOKIE).get(0);
        System.out.println(cookies);

        headers.add(HttpHeaders.COOKIE, cookies);

        // Add a new user
        HttpEntity<User> request1 = new HttpEntity<>(user, headers);
        ResponseEntity<String> response1 = restTemplate.exchange(url, HttpMethod.POST, request1, String.class);
        result.append(response1.getBody());
        System.out.println(result);

        //Update added user
        user.setName("Thomas");
        user.setLastName("Shelby");

        HttpEntity<User> request2 = new HttpEntity<>(user, headers);
        ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.PUT, request2, String.class);
        result.append(response2.getBody());
        System.out.println(result);

        //Delete user
        HttpEntity<User> request3 = new HttpEntity<>(user, headers);
        ResponseEntity<String> response3 = restTemplate.exchange(url + "/" + user.getId(), HttpMethod.DELETE, request2, String.class);
        result.append(response3.getBody());
        System.out.println(result);


    }
}
