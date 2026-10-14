package com.example.student_api_testing.client;

import java.util.List;

import com.example.student_api_testing.client.model.ExternalPost;

public interface ExternalPostClient {
    List<ExternalPost> fetchPosts();
}