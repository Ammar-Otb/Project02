package com.example.ecommerce.contollers;

import com.example.ecommerce.models.Api;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.Comment;
import com.example.ecommerce.models.User;
import com.example.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;


@RestController @RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }
    @PostMapping("/")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        }
        userService.addUser(user);
        return ResponseEntity.status(201).body(new Api("Successfully added user", 201));
    }
    @PutMapping("/{index}")
    public ResponseEntity updateUser(@RequestBody @Valid User user, @PathVariable Integer index, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        } else if (userService.updateUser(index, user)) {
            return ResponseEntity.status(201).body(new Api("Successfully updated user", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index", 400));
    }
    @PutMapping("/add/to/cart")
    public ResponseEntity addToCart(@RequestParam String userId,@RequestParam String prodId){
        Integer addStatus = userService.addToCart(userId, prodId);
        if(addStatus == 0){
            return ResponseEntity.status(201).body(new Api("Successfully added to cart", 201));
        } else if (addStatus == -1) {
            return ResponseEntity.status(400).body(new Api("Invalid product id", 400));
        }
        return ResponseEntity.status(400).body(new Api("Invalid user id", 400));
    }

    @PutMapping("/add/to/stock")
    public ResponseEntity addToMerchantStock(@RequestParam String prodId,@RequestParam String merchId, @RequestParam Integer amount){
        Integer addStatus = userService.addToMerchantStock(prodId, merchId,amount);
        if(addStatus == 0){
            return ResponseEntity.status(201).body(new Api("Successfully added to to merchant stock", 201));
        } else if (addStatus == -1) {
            return ResponseEntity.status(400).body(new Api("Invalid merchant id", 400));
        }
        return ResponseEntity.status(400).body(new Api("Invalid product id", 400));
    }

    @DeleteMapping("/")
    public ResponseEntity deleteUser(@RequestParam String userId){
        if(userService.deleteUser(userId)){
            return ResponseEntity.status(201).body(new Api("Successfully delete user", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid user id", 400));
    }

    @PutMapping("/purchase")
    public ResponseEntity directPurchase(@RequestParam String userId, @RequestParam  String prodId, @RequestParam String merchantId){
        Integer purchaseStatus = userService.directPurchase(userId, prodId, merchantId);
        return switch (purchaseStatus) {
            case 0 -> ResponseEntity.status(201).body(new Api("Successfully purchased", 201));
            case 1 -> ResponseEntity.status(400).body(new Api("Invalid merchant id", 400));
            case -1 -> ResponseEntity.status(400).body(new Api("Not in stock or insufficient funds", 400));
            case 2 -> ResponseEntity.status(400).body(new Api("Invalid product id", 400));
            case 3 -> ResponseEntity.status(400).body(new Api("Invalid user id", 400));
            default -> ResponseEntity.status(404).body(new Api("Unknown error", 404));
        };
    }
    @PutMapping("/cart/purchase")
    public ResponseEntity purchaseCart(@RequestBody @Valid Cart cart, Errors error){
        Integer cartStatus = userService.purchaseCart(cart);
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        }
        return switch (cartStatus) {
            case 1 -> ResponseEntity.status(201).body(new Api("Successfully purchased", 201));
            case -1 -> ResponseEntity.status(400).body(new Api("Insufficient funds", 400));
            case 0 -> ResponseEntity.status(400).body(new Api("Not in stock", 400));
            default -> ResponseEntity.status(404).body(new Api("Unknown error", 404));
        };
    }
    @PutMapping("/comment/{userId}/{prodId}")
    public ResponseEntity commentPost(Errors error, @PathVariable String userId,@PathVariable String prodId,@RequestBody Comment comment){
        Integer commentStatus = userService.commentPost(userId, prodId, comment);
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        }
        return switch (commentStatus) {
            case 1 -> ResponseEntity.status(400).body(new Api("Invalid user id", 400));
            case -1 -> ResponseEntity.status(400).body(new Api("Did not purchase item", 400));
            case 0 -> ResponseEntity.status(200).body(new Api("comment added", 200));
            default -> ResponseEntity.status(404).body(new Api("Unknown error", 404));
        };
    }
    @GetMapping("/all/comments")
    public ResponseEntity getComments(String prodId){
        ArrayList comments = userService.getComments(prodId);
        if( comments != null){
           return ResponseEntity.status(200).body(comments);
        }
        return ResponseEntity.status(400).body(new Api("Invalid user id", 400));
    }
    @GetMapping("/rate")
    public ResponseEntity getFiveStarsRated(){
        return ResponseEntity.status(200).body(userService.getFiveStarsRated());
    }
    @GetMapping("history")
    public ResponseEntity getPurchaseHistory(){
        return ResponseEntity.status(200).body(userService.getPurchaseHistory());
    }


}
