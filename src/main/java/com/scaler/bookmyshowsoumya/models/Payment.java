package com.scaler.bookmyshowsoumya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
//@Entity
public class Payment extends BaseModel{
    private String transactionNumber;
    @Enumerated
    private PaymentStatus paymentStatus;
    private  double amount;
    @Enumerated
    private PaymentType paymentType;
}
