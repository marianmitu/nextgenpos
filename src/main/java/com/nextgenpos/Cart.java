package com.nextgenpos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author steau
 */
public class Cart {

    protected ArrayList<Product> inventory;

    private double subTotal;
    private double cashIn;

    public Cart() {
        this.subTotal = 0.0;
        inventory = new ArrayList<>();
    }

    public void add(Product item) {
        inventory.add(item);
        this.subTotal += item.getPrice();
    }

    public void addMultItems(Product item, int q) {
        for (int i = 0; i < q; i++) {
            this.add(item);
        }
    }

    public ArrayList getInventory() {
        return this.inventory;
    }

    public void removeItem(int itemNumber) throws InterruptedException, IOException {

        for (int i = 0; i < inventory.size(); i++) {
            if (itemNumber == inventory.get(i).getId()) {

                System.out.println(inventory.get(i).getItemName() + " was removed from cart!");
                subTotal -= inventory.get(i).getPrice();
                inventory.remove(i);
                break;
            } else {
                if (i == inventory.size() - 1) {
                    System.out.println("No such item exists in this Cart.");
                }
            }
        }
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void clearSubTotal() {
        this.subTotal = 0;
    }

}
