package com.ftn.student.service.errorhandling;

public class JsonResponse {

	private String message;

    public JsonResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
