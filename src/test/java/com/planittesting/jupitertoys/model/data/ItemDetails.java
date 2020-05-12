package com.planittesting.jupitertoys.model.data;

public class ItemDetails {
    private String name;
    private String price;
    private String quantity;
    private String currency;

    public ItemDetails setName(String name) {
        this.name = name;
        return this;
    }

    public ItemDetails setPrice(String price) {
        this.price = price;
        return this;
    }

    public ItemDetails setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public ItemDetails setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getName() { return this.name; }

    public String getPrice() { return this.price; }

    public String getQuantity() { return this.quantity; }

    public String getCurrency() { return currency; }

    public String getSubtotal() {
        String price = this.price.replaceAll("[^\\d.]", "");
        Double subtotal = Double.parseDouble(price) * Integer.parseInt(this.quantity);
        return subtotal.toString();
    }

}