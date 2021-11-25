
package com.nextgenpos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author steau
 */
public class Sale extends Register {

    private double total;
    private boolean nextItem;
    private final int endOfCart;
    private final int removeItem;
    private int input;
    private final int cancelTransaction;
    private static final Double taxPercent = .19;
    private ArrayList<int[]> changes;
    Cart currentCart = new Cart();

    public Sale() {
        this.nextItem = true;
        this.endOfCart = -999;
        this.removeItem = -1;
        this.cancelTransaction = -190;
        this.input = 0;
    }

    public void makeTransaction() throws InterruptedException, IOException {
        changes = new ArrayList<int[]>();
        Scanner transaction = new Scanner(System.in);
        ProductCatalog catalog = ProductCatalog.getInstance();
        while (nextItem) {
            try {
                System.out.print("Enter item ID");
                System.out.print("[OPTIONS: -999 for end of sale, -1 to remove an item, -190 to cancel transaction]\n-->");
                if (transaction.hasNextInt()) {
                    input = transaction.nextInt();
                    if (input == endOfCart) {
                        nextItem = false;
                    } else if (input == removeItem) {
                        if (currentCart.inventory.isEmpty()) {
                            System.out.println("NO ITEMS TO BE REMOVED");
                            continue;
                        }
                        System.out.print("Enter an item to remove\n-->");
                        if (!transaction.hasNextInt()) {
                            throw new InputMismatchException();
                        }
                        currentCart.removeItem(transaction.nextInt());
                    } else if (input == cancelTransaction) {
                        cancelTransaction(changes);
                        Cashier.cashierDo();
                        break;
                    } else {
                        System.out.print("Enter quantity of item to be purchased\n-->");
                        if (!transaction.hasNextInt()) {
                            throw new InputMismatchException();
                        }
                        int itemQuan = transaction.nextInt();
                        if (itemQuan < 1) {
                            throw new InputMismatchException();
                        } else {
                            if(catalog.getProductById(input).getId()==input){
                                currentCart.addMultItems(catalog.getProductById(input), itemQuan);                                
                            }
                        }
                    }
                } else {
                    System.out.println("INVALID INPUT...Try Again");
                    System.out.println();
                    transaction.nextLine();
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error reading input, try again");
                System.out.println();
                transaction.nextLine();
            }
            System.out.printf("\nCurrent Cart Subtotal : %.2f", this.currentCart.getSubTotal());
            System.out.print("\n\n");

        }

        int pt = getPaymentType();

        if (registerPay(pt)) {
            boolean paid = false;
            while (!paid) {
                if (pt == 0) { // pt=0 plata cash, pt=1 plata card
                    Scanner cashIn = new Scanner(System.in);
                    System.out.printf("Cart Total: %.2f\n", (this.currentCart.getSubTotal()));
                    System.out.print("Enter cash recieved or enter -999 to pay by card\n-->");
                    double c = 0.0;
                    if (cashIn.hasNextDouble()) {
                        c = cashIn.nextDouble();
                        if (c == -999) {
                            pt = 1;
                            System.out.println("\nPaying By Card...");
                            continue;
                        } else if (c <= 0) {
                            System.out.println("Please Enter a valid amount...\n");
                            continue;
                        }
                        double change = makeChange(c, currentCart.getSubTotal()); // update la subtotal de plata
                        //Aici trebuie implementat "bonul"
                        System.out.printf("Your change is %.2f.\n", change);
                        paid = true;
                        continue;
                    }
                    System.out.println("Please Enter a valid amount...\n");
                    continue;
                } else if (pt == 1) {
                    // plata cu cardul de implementat
                }
            }
        }
    }

    public void cancelTransaction(ArrayList<int[]> changes) throws InterruptedException, IOException {

        System.out.println("Transaction was cancelled...CART IS NOW EMPTY!");
        currentCart.inventory.clear();
        currentCart.clearSubTotal();

        int id;
        int quantity;

        for (int[] pair : changes) {

            id = pair[0];
            quantity = pair[1];
        }
    }

    public double makeChange(double cash, double total) {
        double ret = 0.0;
        Scanner cashIn = new Scanner(System.in);
        if (cash >= total) {
            ret = cash - total;

        } else if (cash < total) {
            System.out.printf("Insufficient Funds!\nRemaining Amount Due: $"+ (total - cash)+ "\n");
            double c = 0.0;
            while (true) {
                System.out.print("Enter more money to complete the sale:\n-->");
                if (cashIn.hasNextDouble()) {
                    c = cashIn.nextDouble();
                    if (c > 0.0) {
                        break;
                    } else {
                        System.out.println("Please enter a valid sum of money...\n");
                    }
                } else {
                    System.out.println("Please enter a valid sum of money...\n");
                    cashIn.nextLine();
                }
            }

            cash = cash + c;
            ret = makeChange(cash, total);

        }
        return ret;
    }
}
