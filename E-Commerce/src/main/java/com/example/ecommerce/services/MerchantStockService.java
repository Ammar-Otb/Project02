package com.example.ecommerce.services;

import com.example.ecommerce.models.MerchantStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service @RequiredArgsConstructor
public class MerchantStockService {
    private ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final MerchantService merchantService;

    public MerchantStock findMerchantById(String merchantId){
        for(MerchantStock ms: merchantStocks){
            if(ms.getId().equals(merchantId)){
                return ms;
            }
        }
        return null;
    }

    public ArrayList<MerchantStock> getMerchantStocks(){
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock m){
        merchantStocks.add(m);
    }

    public Boolean updateMerchantStock(Integer index, MerchantStock merchantStock){
        if(index > merchantStocks.size()-1){
            return false;
        }
        merchantStocks.set(index, merchantStock);
        return true;
    }

    public Boolean deleteMerchantStocks(String merchantStockId){
        for(MerchantStock p: merchantStocks){
            if (merchantStockId.equals(p.getId())){
                merchantStocks.remove(p);
                return true;
            }
        }
        return false;
    }

}
