package com.example.ecommerce.contollers;

import com.example.ecommerce.models.Api;
import com.example.ecommerce.models.MerchantStock;
import com.example.ecommerce.services.MerchantStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController @RequiredArgsConstructor
@RequestMapping("api/v1/MerchantStock")
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/")
    public ResponseEntity getMerchantStocks(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }
    @PostMapping("/")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(201).body(new Api("Successfully added MerchantStock", 201));
    }
    @PutMapping("/{index}")
    public ResponseEntity updateMerchantStock(@RequestBody @Valid MerchantStock merchantStock, @PathVariable Integer index, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        } else if (merchantStockService.updateMerchantStock(index, merchantStock)) {
            return ResponseEntity.status(201).body(new Api("Successfully updated MerchantStock", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index", 400));
    }

    @DeleteMapping("/")
    public ResponseEntity deleteMerchantStock(@RequestParam String merchantStockId){
        if(merchantStockService.deleteMerchantStocks(merchantStockId)){
            return ResponseEntity.status(201).body(new Api("Successfully delete MerchantStock", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid MerchantStock id", 400));
    }


}
