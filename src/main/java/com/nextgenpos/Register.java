/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nextgenpos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author steau
 */
public class Register {
    
    private int paymentType;
    Scanner readPaymentType = new Scanner(System.in);
    
     public int getPaymentType() {
        boolean valid = false;

        
        System.out.print("Enter payment method-");
        System.out.print("[OPTIONS: 0 for Cash, 1 for Credit Card]\n-->"); 

        while(!valid)
        {
            try
            {
                this.paymentType = readPaymentType.nextInt();
                valid = true;
            }catch(InputMismatchException e)
            {
                valid = false;
                System.out.println("Invalid Input! Try Again\n-->");
            }
        }

        return this.paymentType;
    }
     
    public boolean registerPay(int paymentType) {
        return paymentType != -1;
    }
    
    public static void main(String args[]) throws InterruptedException, IOException {
        //Login l = Login.getInstance();
        Register reg = new Register();
        boolean done = false;
        
        do {
            System.out.println("Welcome to The Pandas' Next Gen POS!");
            System.out.println("************************************");
            System.out.println("************ Main Menu *************");
            System.out.println("***********************************");
            int rout = -1; // -1 for cashier, 0 for manager
            if (rout == -1)
                Cashier.cashierDo();            
        } while (!done);
    }
}
