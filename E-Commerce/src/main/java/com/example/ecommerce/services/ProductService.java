package com.example.ecommerce.services;

import com.example.ecommerce.models.Product;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class ProductService {
    private ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts(){
        return products;
    }

    public void addProduct(Product p){
        products.add(p);
    }

    public Boolean updateProduct(Integer index, Product product){
        if(index > products.size()-1){
            return false;
        }
        products.set(index, product);
        return true;
    }

    public Boolean deleteProduct(String productId){
        for(Product p: products){
            if (productId.equals(p.getId())){
                products.remove(p);
                return true;
            }
        }
        return false;
    }

}
