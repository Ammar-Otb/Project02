package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.security.DenyAll;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data @AllArgsConstructor
public class PurchaseHistory {
    @NotEmpty
    @Size(min = 3, max = 3)
    private String id;
    @NotEmpty @Size(min = 3, max = 3)
    private String userId;
    @NotEmpty @Size(min = 3, max = 3)
    private String productId;
    @NotEmpty @Positive
    private Double price;
}
