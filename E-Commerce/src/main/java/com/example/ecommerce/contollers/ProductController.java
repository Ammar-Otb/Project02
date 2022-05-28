package com.example.ecommerce.contollers;

import com.example.ecommerce.models.Api;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController @RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity getProducts(){
        return ResponseEntity.status(200).body(productService.getProducts());
    }
    @PostMapping("/")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        }
        productService.addProduct(product);
        return ResponseEntity.status(201).body(new Api("Successfully added product", 201));
    }
    @PutMapping("/{index}")
    public ResponseEntity updateProduct(@RequestBody @Valid Product product, @PathVariable Integer index, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        } else if (productService.updateProduct(index, product)) {
            return ResponseEntity.status(201).body(new Api("Successfully updated product", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index", 400));
    }

    @DeleteMapping("/")
    public ResponseEntity deleteProduct(@RequestParam String productId){
        if(productService.deleteProduct(productId)){
            return ResponseEntity.status(201).body(new Api("Successfully delete product", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid product id", 400));
    }


}
