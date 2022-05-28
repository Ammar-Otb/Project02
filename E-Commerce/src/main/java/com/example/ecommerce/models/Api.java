package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data @JsonInclude(JsonInclude.Include.NON_NULL)
public class Api {
    private String message;
    private Integer code;
    private String errorMessage;

    public Api(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
