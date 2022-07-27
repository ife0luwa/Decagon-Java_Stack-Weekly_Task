package models;

public class Product {
    private String name;
    private Double price;


    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice () {
        return this.price;
    }



}


/**
 * store : List<Products> stock;
 *              Buy(UserCustomer, models.Product){
 *                  if(){
 *                      stock.remove.remove
 *                      user.getCart.add
 *                      bal = user.wallet - totalAmount
 *                      user.getWallet.setAmount(bal)
 *                  }
 *              }
 *
 *     models.Product: name, price, qty
 *     models.User: name, Role, List<models.Product> cart, models.Wallet
 *          methodDoSomething(Role);\
 *     models.Wallet: amount
 *
 *
 *
 */