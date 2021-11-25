
package com.nextgenpos;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

/**
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
    }

    public void print() {
        //de implementat
    }
    
    public void store(){
        //de implementat metoda de salvare a vanzarii in baza de date
    }

}
