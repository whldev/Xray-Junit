package com.planittesting.jupitertoys.model.data;

public class PaymentDetails {

    private String cardType;
    private String cardNumber;

    public PaymentDetails() {
        this.cardType = "Visa";
        this.cardNumber = "1111111111111111";
    }

    public PaymentDetails setCardType(String cardType) {
        this.cardType = cardType;
        return this;
    }

    public PaymentDetails setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getCardType() { return cardType; }

    public String getCardNumber() { return cardNumber; }
}
