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
public class Channel {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Market> markets = new ArrayList<>();
    
    public Channel(String name) {
        this.name = name;
    }
    
    public String getName() { return name; } 
    public ArrayList<Customer> getCustomers() { return customers; } 
    public ArrayList<Market> getMarkets() { return markets; }

    public int getCustomerCount() { return customers.size(); } 
    public int getMarketCount() { return markets.size(); }
    
    public void linkMarket(Market m) {
        if (!markets.contains(m)) markets.add(m);
    }

    public void linkCustomer(Customer c) {
        if (!customers.contains(c)) customers.add(c);
    }
}
