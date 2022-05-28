package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import com.example.ecommerce.models.*;

@Data
public class Product {
    @NotEmpty @Size(min = 3, max = 5)
    private String id;
    @NotEmpty @Size(min = 3, max = 25)
    private String name;
    @Positive
    private Double price;
    @NotEmpty @Size(min = 3, max = 5)
    private String categoryId;
    private ArrayList<com.example.ecommerce.models.Comment> comments;

    public Product(String id, String name, Double price, String categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.comments = new ArrayList<>();
    }
    public boolean addComment(com.example.ecommerce.models.Comment comment){
        comments.add(comment);
        return true;
    }
}
