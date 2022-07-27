package dev.koorius.model;

public class Wallet {
    private Double amount;




    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
        System.out.println("model.Wallet balance:$" + amount);
    }
}
