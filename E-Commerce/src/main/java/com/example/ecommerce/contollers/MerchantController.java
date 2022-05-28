package com.example.ecommerce.contollers;

import com.example.ecommerce.models.Api;
import com.example.ecommerce.models.Merchant;
import com.example.ecommerce.services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController @RequiredArgsConstructor
@RequestMapping("api/v1/merchant")
public class MerchantController {
    private final MerchantService MerchantService;

    @GetMapping("/")
    public ResponseEntity getMerchant(){
        return ResponseEntity.status(200).body(MerchantService.getMerchants());
    }
    @PostMapping("/")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merch, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        }
        MerchantService.addMerchant(merch);
        return ResponseEntity.status(201).body(new Api("Successfully added Merchant", 201));
    }
    @PutMapping("/{index}")
    public ResponseEntity updateMerchant(@RequestBody @Valid Merchant merch, @PathVariable Integer index, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(new Api("Invalid Data", 400, error.getFieldError().getDefaultMessage()));
        } else if (MerchantService.updateMerchant(index, merch)) {
            return ResponseEntity.status(201).body(new Api("Successfully updated Merchant", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Index", 400));
    }

    @DeleteMapping("/")
    public ResponseEntity deleteMerchant(@RequestParam String merchId){
        if(MerchantService.deleteMerchant(merchId)){
            return ResponseEntity.status(201).body(new Api("Successfully delete Merchant", 201));
        }
        return ResponseEntity.status(400).body(new Api("Invalid Merchant  id", 400));
    }


}
