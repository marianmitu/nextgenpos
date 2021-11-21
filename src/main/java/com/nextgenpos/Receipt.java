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
    //private Date date = new Date();
    private double rentalDeposit = 0.0;

    public Receipt(Cart c, double t, int pm, int id) {
        this.cart = c;
        this.tax = t;
        this.paymentMethod = pm;
        this.id = id;
    }

    public void print() throws InterruptedException, IOException {
        //IMPORTANT: receipt must be stored before it is printed, in order to get its id
        DecimalFormat df = new DecimalFormat("0.00");
        Date receiptDate = new Date(); //now
        System.out.println("Date: " + receiptDate);
        for (Item item : cart.inventory) { //for each item in cart
            if (item.getIsRental()) {
                System.out.print("(R)");
            }
            System.out.println(item.getName() + "\t\t$" + df.format(item.getPrice()));
        }

        System.out.println("\n\tOrder Subtotal:\t$" + df.format(cart.getSubtotal()));
        double totalTax = this.tax;
        if (cart.getSubtotal() == 0) {
            totalTax = 0;
        }
        System.out.println("\tTotal Tax:\t$" + df.format(totalTax));
        System.out.println("\nOrder Total:\t$" + df.format(cart.getSubtotal() + totalTax));
        System.out.println("Receipt Number: " + this.id);  //this line is why you need to store() before you print()
    }

}
