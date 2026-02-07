/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author danchenluo
 */
public class Market {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Channel> channels = new ArrayList<>();
    private Random rand = new Random();
    
    public Market(String name) {
        this.name = name;
    }
    
    public String getName() { return name; } 
    public ArrayList<Customer> getCustomers() { return customers; } 
    public ArrayList<Channel> getChannels() { return channels; }
    
    public int getCustomerCount() { return customers.size(); }
    
    // === Add channel to market ===
    public void addChannel(Channel ch){
        if (ch == null || channels.contains(ch)) return;
        channels.add(ch);
        ch.linkMarket(this);
    }
    
// === Add customer to this market and assign exactly one random channel from this market ===
    public void addCustomer (Customer c) {
        if (c == null || customers.contains(c)) return;
        customers.add(c);
        c.setMarket(this);
        
        int randomIndex = rand.nextInt(channels.size());
        Channel ch = channels.get(randomIndex);
        ch.linkCustomer(c);
        c.setChannel(ch);
    }
}
