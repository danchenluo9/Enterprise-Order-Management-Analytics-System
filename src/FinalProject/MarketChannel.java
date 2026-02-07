/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package FinalProject;

/**
 *
 * @author danchenluo
 */
public class MarketChannel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Create a new business instance (system setup)
        Business biz = new Business();
        
        // Set up markets and channels
        biz.setupMarketsAndChannels();
        
        // Generate customers and orders
        biz.generateMockBusinessData(
                50, //number of customers
                5   //each customer makes up to 5 orders
        );
        
        // Print reports
        biz.printReportByMarket();
        biz.printReportByChannel();
    }
}
