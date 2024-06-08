package com.example.supplychainback.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class Purchase {

    @NotBlank
    @NotNull
    @Size(min=16,max=16)
    String cardNumber;
    @NotBlank
    @NotNull
    @Size(min=3,max=3)
    String cvv;

    @NotBlank
    @NotNull
    @Size(min=16,max=16)
    String expiryDate;

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
