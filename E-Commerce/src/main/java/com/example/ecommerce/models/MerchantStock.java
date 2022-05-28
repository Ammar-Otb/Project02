package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data @AllArgsConstructor
public class MerchantStock {
    @NotEmpty @Size(min = 3, max = 3)
    private String id;
    @NotEmpty @Size(min = 3, max = 3)
    private String productId;
    @NotEmpty @Size(min = 3, max = 3)
    private String merchantId;
    @NotEmpty @Min(10)
    private Integer stock;

}
