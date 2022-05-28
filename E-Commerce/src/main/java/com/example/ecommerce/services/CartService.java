package com.example.ecommerce.services;

import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service @RequiredArgsConstructor
public class CartService {
    private ArrayList<Cart> carts = new ArrayList<>();

    public ArrayList<Cart> getCarts(){
        return carts;
    }







}
