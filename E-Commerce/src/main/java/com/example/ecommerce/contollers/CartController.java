package com.example.ecommerce.contollers;

import com.example.ecommerce.models.Api;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController @RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/")
    public ResponseEntity getCart(){
        return ResponseEntity.status(200).body(cartService.getCarts());
    }
}
