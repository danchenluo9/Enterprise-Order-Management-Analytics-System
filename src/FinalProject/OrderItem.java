/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

/**
 *
 * @author danchenluo
 */
public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product p, int qty) {
        this.product = p;
        this.quantity = qty;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getOrderItemTotal() {
        return quantity * product.getPrice();
    }
}
