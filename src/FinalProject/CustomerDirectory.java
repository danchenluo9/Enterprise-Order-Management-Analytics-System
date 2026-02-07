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
public class CustomerDirectory {
    private ArrayList<Customer> customers;

    public CustomerDirectory() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
