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
public class Customer extends Person {
    private ArrayList<Order> orders;
    private int age;
    private String marketTag;
    private Market m;
    private Channel ch;

    public Customer(String name, String id, String marketTag, int age) {
        super(name, id);
        this.age = age;
        this.marketTag = marketTag;
        orders = new ArrayList<>();
    }
    
    public String getMarketTag() { return marketTag; }
    public Market getMarket() { return m; }
    public Channel getChannel() { return ch; }
    
    public void setMarketTag(String marketTag) { this.marketTag = marketTag; }
    public void setMarket(Market m) { this.m = m; }
    public void setChannel(Channel ch) { this.ch = ch; }

    public void addOrder(Order o) {
        orders.add(o);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    public int getOrderCount(){
        return orders.size();
    }
    
    public double getCustomerOrderTotal(){
        double total = 0.00;
        for(Order o : orders){
            total += o.getOrderTotal();
        }
        return total;
    }
}

