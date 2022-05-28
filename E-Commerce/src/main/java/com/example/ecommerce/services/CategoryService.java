package com.example.ecommerce.services;

import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    private ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories(){
        return categories;
    }

    public void addCategory(Category c){
        categories.add(c);
    }

    public Boolean updateCategory(Integer index, Category c){
        if(index > categories.size()-1){
            return false;
        }
        categories.set(index, c);
        return true;
    }

    public Boolean deleteCategory(String categoryId){
        for(Category c: categories){
            if (categoryId.equals(c.getId())){
                categories.remove(c);
                return true;
            }
        }
        return false;
    }

}
