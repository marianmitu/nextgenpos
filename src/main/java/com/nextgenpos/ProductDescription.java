/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nextgenpos;

/**
 *
 * @author steau
 */
public class ProductDescription {

    private ItemID id;
    private double price;
    private String description;

    public ProductDescription(ItemID id, double price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public ItemID getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

}
