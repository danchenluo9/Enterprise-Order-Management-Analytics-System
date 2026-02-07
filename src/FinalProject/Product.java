/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danchenluo
 */
public class Product {
    private String id;

    private String name;
    private double price;
    
    // Stores all OrderItems that include this product.
    List<OrderItem> orderitems;

    public Product(String name,String id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.orderitems = new ArrayList<>();
        
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    public List<OrderItem> getOrderItems() {
        return orderitems;
    }

    public void addOrderItem(OrderItem item) {
        orderitems.add(item);
    }    
}
