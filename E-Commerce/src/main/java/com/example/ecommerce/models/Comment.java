package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data @AllArgsConstructor
public class Comment {
    @NotEmpty
    @Size(min = 3, max = 3)
    private String id;
    @NotEmpty @Size(min = 5, max = 5)
    private String userId;
    @NotEmpty @Range(min =1, max = 5)
    private Integer rate;
    @NotEmpty @Size(min = 6, max = 50)
    private String message;


}
