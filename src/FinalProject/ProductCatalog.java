/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

import java.util.ArrayList;

/**
 *
 * @author danchenluo
 */
public class ProductCatalog {
    private ArrayList<Product> products;

    public ProductCatalog() {
        products = new ArrayList<>();
    }

    public Product addProduct(String name, String id, double price) {
        Product p = new Product(name, id, price);
        products.add(p);
        return p;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
