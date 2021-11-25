
package com.nextgenpos;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 *
 * @author steau
 */
public class ProductCatalog {

    private static ProductCatalog instance;
    private List<Product> catalog;

    private ProductCatalog() {
        catalog = new Vector<Product>();
    }

    public static ProductCatalog getInstance() {
        if (instance == null) {
            instance = new ProductCatalog();
        }
        return instance;
    }

    public void addProductToCatalog(Product product) {
        catalog.add(product);
    }

    public List<Product> getProductCatalog() {
        return this.catalog;
    }

    public Product getProductById(int id) {
        Product requestedProduct = null;
        for (Product product : catalog) {
            if (product.getId() == id) {
                requestedProduct = product;
            }
        }
        return requestedProduct;
    }
}
