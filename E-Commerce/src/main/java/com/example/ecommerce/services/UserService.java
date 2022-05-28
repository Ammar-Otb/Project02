package com.example.ecommerce.services;

import com.example.ecommerce.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service @RequiredArgsConstructor
public class UserService {
    private ArrayList<User> users = new ArrayList<>();
    private final ProductService productService;
    private final CartService cartService;
    private final MerchantStockService merchantStockService;
    private final PurchaseHistoryService purchaseHistoryService;


    public ArrayList getPurchaseHistory() {
        return purchaseHistoryService.getPurchaseHistorys();
    }

    public ArrayList getComments(String prodId){
        Product product = searchProduct(prodId);
        if(product != null){
            return product.getComments();
        }
        return null;
    }

    public ArrayList<Product> getFiveStarsRated(){
        ArrayList<Product> productsFiveStarts = new ArrayList<>();
        for(Product prod : productService.getProducts()){
            for(Comment cm: prod.getComments()){
                if (cm.getRate() == 5){
                    productsFiveStarts.add(prod);
                }
            }
        }
        return productsFiveStarts;
    }

    public Integer commentPost(String userId,String prodId, Comment comment){
       for(PurchaseHistory p: purchaseHistoryService.getPurchaseHistorys()){
           if(p.getUserId().equals(userId)){
               if (p.getProductId().equals(prodId)){
                   searchProduct(prodId).getComments().add(comment);
                   return 0; //comment added
               }
               return -1; //Did not purchase item
           }
       }
       return 1;//Invalid user id
    }





    public Integer purchaseCart(Cart cart){
        double total= 0;
        double userBalance = validateUser(cart.getUserId()).getBalance();
        for(Product product: cart.getProducts()){
            total += product.getPrice();
            for(MerchantStock ms : merchantStockService.getMerchantStocks()){
                if(ms.getProductId().equals(product.getId())){
                    if(ms.getStock() > 1){
                        ms.setStock(ms.getStock()-1);
                    }
                    return 0; //out of stock
                }
            }
        }
        if(userBalance >= total){
           validateUser(cart.getUserId()).setBalance(userBalance - total);
           return 1; //Successfully purchased
        }
        return -1; //Insufficient funds

    }

    public Integer addToCart(String userId, String productId){
        Product product = searchProduct(productId);
        User user = validateUser(userId);
        if(user != null){
            if(product != null){
                Cart a = new Cart(userId, productId);
                a.addProduct(product);
                cartService.getCarts().add(a);
                return 0;//Successfully added to cart
            }
            return -1; //Invalid product id
        }
        return 1; //invalid user id
    }

    public Integer addToMerchantStock(String prodId, String merchantId, Integer stock){
        Product pro = searchProduct(prodId);
        for(MerchantStock m : merchantStockService.getMerchantStocks()){
            if(m.getMerchantId().equals(merchantId)){
                m.setStock(m.getStock()+stock);
                return 0; //Added to stock
            }
            return -1; //Merchant id not found
        }
        return 1;//invalid product id
    }
    public String randomIdGenerator(){
        String nums = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 3; i++) {
            int index = random.nextInt(nums.length());
            char randomChar = nums.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();

    }

    public Integer directPurchase(String userid, String prodId, String merchantId){
        User user = validateUser(userid);
        Product product = searchProduct(prodId);
        MerchantStock ms = merchantStockService.findMerchantById(merchantId);
        if(user != null){
            if(product != null){
                if(ms != null){
                    if(!(ms.getStock() != 0 && user.getBalance() >= product.getPrice())){
                        return -1; //Not in stock or insufficient funds
                    }
                    ms.setStock(ms.getStock()-1);
                    user.setBalance(user.getBalance()-product.getPrice());
                    purchaseHistoryService.getPurchaseHistorys().add(new
                            PurchaseHistory(randomIdGenerator(),
                            userid, prodId, product.getPrice()));
                    return 0; //Successful purchase
                }
                return 1; //Invalid merchant id
            }
            return 2; //Invalid product id
        }
        return 3;//Invalid user id
    }

    public Product searchProduct(String productId){
        for(Product prod: productService.getProducts()){
            if(prod.getId().equals(productId)){
                return prod;
            }
        }
        return null;
    }



    public User validateUser(String userId){
        for(User usr: users){
            if(usr.getId().equals(userId)){
                return usr;
            }
        }
        return null;
    }


    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User p){
        users.add(p);
    }

    public Boolean updateUser(Integer index, User user){
        if(index > users.size()-1){
            return false;
        }
        users.set(index, user);
        return true;
    }

    public Boolean deleteUser(String userId){
        for(User p: users){
            if (userId.equals(p.getId())){
                users.remove(p);
                return true;
            }
        }
        return false;
    }


}
