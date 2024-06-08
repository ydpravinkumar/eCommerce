package com.example.supplychainback.API.Controller.Auth;

import com.example.supplychainback.API.Model.PurchaseBody;
import com.example.supplychainback.Service.CartServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/purchase")
/**
 * Validate all the card details like number, cvv and expiry to process the transaction
 */
public class purchaseController {
    @Autowired
    private CartServices cartServices;

    @PostMapping("/status")
    public ResponseEntity<?> purchaseMade(@Valid @RequestBody PurchaseBody purchaseBody) {
        try {

            if (isValidPurchase(purchaseBody)) {
                return ResponseEntity.ok("Transaction Successful");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Transaction Details");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing transaction");
        }
    }
    private boolean isValidPurchase(PurchaseBody purchaseBody) {
        return purchaseBody.getCardNumber().startsWith("4");
    }
}
