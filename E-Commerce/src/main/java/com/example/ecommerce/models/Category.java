package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;

@Data @AllArgsConstructor
public class Category {
    @NotEmpty @Size(min = 3, max = 3)
    private String id;
    @NotEmpty @Size(min = 3, max = 3)
    private String name;

}
