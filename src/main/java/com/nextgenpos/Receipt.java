/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nextgenpos;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author steau
 */
public class Receipt {

    private Cart cart;
    private double tax;
    private int paymentMethod;
    private int id = -999;

    public Receipt(Cart c, double t, int pm) {
        this.cart = c;
        this.tax = t;
        this.paymentMethod = pm;
        //this.id = id;
    }

    public void print() throws InterruptedException, IOException {
        DecimalFormat df = new DecimalFormat("0.00");
        Date receiptDate = new Date(); //now
        System.out.println("Date: " + receiptDate);
        for (Product item : cart.inventory) { //for each item in cart
            System.out.println(item.getItemName() + "\t\t$" + df.format(item.getPrice()));
        }
        System.out.println("\n\tOrder Subtotal:\t$" + df.format(cart.getSubTotal()));
        double totalTax = this.tax;
        if (cart.getSubTotal() == 0) {
            totalTax = 0;
        }

        System.out.println("\tTotal Tax:\t$" + df.format(totalTax));
        System.out.println("\nOrder Total:\t$" + df.format(cart.getSubTotal() + totalTax));
        System.out.println("Receipt Number: " + this.id);  //this line is why you need to store() before you print()
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void store(){
        //de implementat metoda de salvare a vanzarii in baza de date
    }

}
