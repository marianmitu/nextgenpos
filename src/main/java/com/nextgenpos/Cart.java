/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nextgenpos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author steau
 */
public class Cart {

    protected ArrayList<Item> inventory;

    private double subTotal;
    private double cashIn;
    private int currentDay;

    public Cart() {
        this.subTotal = 0.0;
        inventory = new ArrayList<>();
        this.setCurrentDay();
    }

    public void add(Item item) {
        inventory.add(item);
        this.subTotal += item.getPrice();
    }

    public Integer getStartDate() {
        return this.currentDay;
    }

    public ArrayList getInventory() {
        return this.inventory;
    }

    public void removeItem(int itemNumber) throws InterruptedException, IOException {
        
        for (int i = 0; i < inventory.size(); i++) {
            if (itemNumber == inventory.get(i).getItemNumber()) {

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
    
    // data necesara in cazul unui retur 
    public void setCurrentDay(){
        this.currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);    
    }
    
}
