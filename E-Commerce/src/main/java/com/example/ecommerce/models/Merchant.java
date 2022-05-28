package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data @AllArgsConstructor
public class Merchant {
    @NotEmpty @Size(min = 3, max = 3)
    private String id;
    @NotEmpty @Size(min = 3, max = 3)
    private String name;

}
