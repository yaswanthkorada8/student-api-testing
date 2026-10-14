package com.example.student_api_testing.client.model;

public record ExternalPost (
		Long userId,
		Long id,
		String title,
		String body
) {}
