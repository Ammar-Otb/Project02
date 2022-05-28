package com.example.ecommerce.contollers;

import com.example.ecommerce.models.Api;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.services.CategoryService;
import com.example.ecommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController @RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryhController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity getCategory(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }
    @PostMapping("/")
    public ResponseEntity addCategory(@RequestBody @Valid Category cat, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        }
        categoryService.addCategory(cat);
        return ResponseEntity.status(201).body(new Api("Successfully added Category", 201));
    }
    @PutMapping("/{index}")
    public ResponseEntity updateCategory(@RequestBody @Valid Category cat, @PathVariable Integer index, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        } else if (categoryService.updateCategory(index, cat)) {
            return ResponseEntity.status(201).body(new Api("Successfully updated Category", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index", 400));
    }

    @DeleteMapping("/")
    public ResponseEntity deleteCategory(@RequestParam String catId){
        if(categoryService.deleteCategory(catId)){
            return ResponseEntity.status(201).body(new Api("Successfully delete Category", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Category  id", 400));
    }


}
