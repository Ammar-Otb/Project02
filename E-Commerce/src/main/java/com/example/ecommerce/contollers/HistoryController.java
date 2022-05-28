package com.example.ecommerce.contollers;

import com.example.ecommerce.services.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController @RequiredArgsConstructor
@RequestMapping("api/v1/purchaseHistory")
public class HistoryController {
    private final PurchaseHistoryService purchaseHistoryService;

    @GetMapping("/")
    public ResponseEntity getPurchaseHistory(){
        return ResponseEntity.status(200).body(purchaseHistoryService.getPurchaseHistorys());
    }
}
