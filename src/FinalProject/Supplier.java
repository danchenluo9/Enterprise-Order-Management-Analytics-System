/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

import java.util.ArrayList;

/**
 * Supplier — represents a company or person who provides products.
 * Inherits from Person so it can reuse name and other common attributes.
 * Each supplier has its own ProductCatalog to manage their products.
 *
 * @author danchenluo
 */
public class Supplier extends Person {
    private ProductCatalog productCatalog;

    public Supplier(String name, String id) {
        super(name, id); // call Person(String name)
        this.productCatalog = new ProductCatalog();
    }

    public Product addProduct(String name, String id, double price) {
        return productCatalog.addProduct(name, id, price);
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public ArrayList<Product> getProducts() {
        return productCatalog.getProducts();
    }
}
