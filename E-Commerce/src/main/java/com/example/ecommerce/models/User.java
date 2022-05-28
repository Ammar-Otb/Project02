package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.*;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;

@Data @AllArgsConstructor
public class User {
    @NotEmpty
    @Size(min = 3, max = 3)
    private String id;
    @NotEmpty @Size(min = 5, max = 5)
    private String username;
    @NotEmpty
//    @Pattern(regexp = "([A-Za-z]+[\\d]+)")
    private String password;
    @Email @NotEmpty
    private String email;
    @NotEmpty @Pattern(regexp = "(Admin|Customer)")
    private String role;
    @NotNull @Positive
    private Double balance;



}
