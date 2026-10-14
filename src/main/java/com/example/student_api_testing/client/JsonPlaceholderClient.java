package com.example.student_api_testing.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.example.student_api_testing.client.model.ExternalPost;

@Component
public class JsonPlaceholderClient implements ExternalPostClient {

    private final RestClient restClient;

    public JsonPlaceholderClient(RestClient.Builder builder) {
        this.restClient = builder
        		.baseUrl("https://jsonplaceholder.typicode.com")
        		.build();
    }
    
    @Override
    public List<ExternalPost> fetchPosts() {
        ExternalPost[] posts = restClient.get()
                .uri("/posts")
                .retrieve()
                .body(ExternalPost[].class);

        if (posts == null) {
            return List.of();
        }
        return Arrays.asList(posts);
    }
}