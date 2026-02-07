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
public class MasterOrderList {
    private ArrayList<Order> orders;

    public MasterOrderList() {
        orders = new ArrayList<>();
    }
    
    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    public Order createOrder(Customer c, String id) {
        Order o = new Order(id,c);
        orders.add(o);
        return o;
    }

    public OrderItem addOrderItem(Order order, Product p, int qty) {
        OrderItem item = new OrderItem(p, qty);
        order.getOrderItems().add(item);
        p.addOrderItem(item);
        return item;
    }
}
