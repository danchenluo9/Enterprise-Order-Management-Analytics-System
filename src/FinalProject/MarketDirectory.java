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
public class MarketDirectory {
    private List<Market> markets = new ArrayList<>();

    public void addMarket(Market m) {
        markets.add(m);
    }

    public List<Market> getMarkets() {
        return markets;
    }
    
    public void assignCustomerByTag(Customer c) {
        if (c == null) return;
        
        for (Market m : markets) {
            if (m.getName().equalsIgnoreCase(c.getMarketTag())) {
                m.addCustomer(c);
                break;
            }
        }
    }
}
