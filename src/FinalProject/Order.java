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
public class Order {
    private String id;


    private Customer customer;
    private ArrayList<OrderItem> orderItems;

    public Order(String id, Customer c) {
        this.id = id;
        this.customer = c;
        this.orderItems = new ArrayList<>();
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    public String getId() {
        return id;
    }
    
    public double getOrderTotal() {
        double total = 0.00;
        for (OrderItem item : orderItems) {
            total += item.getOrderItemTotal();
        }
        return total;
    }
}
