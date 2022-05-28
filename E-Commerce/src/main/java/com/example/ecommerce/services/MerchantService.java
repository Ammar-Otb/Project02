package com.example.ecommerce.services;

import com.example.ecommerce.models.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
    private ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getMerchants(){
        return merchants;
    }

    public void addMerchant(Merchant m){
        merchants.add(m);
    }

    public Boolean updateMerchant(Integer index, Merchant merchant){
        if(index > merchants.size()-1){
            return false;
        }
        merchants.set(index, merchant);
        return true;
    }

    public Boolean deleteMerchant(String merchantId){
        for(Merchant p: merchants){
            if (merchantId.equals(p.getId())){
                merchants.remove(p);
                return true;
            }
        }
        return false;
    }

}
