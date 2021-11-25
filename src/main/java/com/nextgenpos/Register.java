
package com.nextgenpos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author steau
 */
public class Register {

    private int paymentType;
    Scanner readPaymentType = new Scanner(System.in);
    public List<Product> catalog = new Vector<>();

    public Register() {
    }

    public int getPaymentType() {
        boolean valid = false;

        System.out.print("Enter payment method-");
        System.out.print("[OPTIONS: 0 for Cash, 1 for Credit Card]\n-->");

        while (!valid) {
            try {
                this.paymentType = readPaymentType.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
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
        Register reg = new Register();
        boolean done = false;
        //creeam un catalog local si adaugam cateva date pentru test
        ProductCatalog catalog = ProductCatalog.getInstance();
        Product product1 = new Product(10.99, 1, "Kiwi", 30);
        catalog.addProductToCatalog(product1);

        do {
            System.out.println("************************************");
            System.out.println("************ Main Menu *************");
            System.out.println("************************************");
            Cashier.cashierDo();
        } while (!done);

        //loop
    }
}
