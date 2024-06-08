package com.example.supplychainback.API.Model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PurchaseBody {

    private Long Id;

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

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
    @Size(min=4,max=4)
    String expiryDate;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
