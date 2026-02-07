package FinalProject;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author danchenluo
 */
public class Business {
    
    // === Core components ===
    private CustomerDirectory customerDirectory;
    private MasterOrderList masterOrderList;
    private SupplierDirectory supplierDirectory;
    private ChannelDirectory channelDirectory;
    private MarketDirectory marketDirectory;
    
    // === Helpers for data generation (names, ids, random numbers) ===
    private Faker faker;
    private Random rand;

    public Business() {
        customerDirectory = new CustomerDirectory();
        masterOrderList = new MasterOrderList();
        supplierDirectory = new SupplierDirectory(); 
        channelDirectory = new ChannelDirectory();
        marketDirectory = new MarketDirectory();
        faker = new Faker();
        rand = new Random();
    }

    // Getters (for external access)
    public CustomerDirectory getCustomerDirectory() { return customerDirectory; }
    public MasterOrderList getMasterOrderList() { return masterOrderList; }
    public SupplierDirectory getSupplierDirectory() { return supplierDirectory; }
    public ChannelDirectory getChannelDirectory() { return channelDirectory; }
    public MarketDirectory getMarketDirectory() { return marketDirectory; }
    
    public Order createOrder(Customer c, String id) {
        Order o = masterOrderList.createOrder(c,id);
        c.addOrder(o);
        return o;
    }

    public OrderItem addOrderItem(Order order, Product p, int qty) {
        return masterOrderList.addOrderItem(order, p, qty);
    }
    
    // ==========================================================
    // 1. Create Markets And Channels
    // ==========================================================
    public void setupMarketsAndChannels() {
        // Create markets and register markets in the directory
        Market m1 = new Market("Youth Market");
        Market m2 = new Market("Mid-Age Market");
        Market m3 = new Market("Elder Market");
        
        marketDirectory.addMarket(m1);
        marketDirectory.addMarket(m2);
        marketDirectory.addMarket(m3);

        // Create channels and register channels in the directory
        Channel c1 = new Channel("Tiktok");
        Channel c2 = new Channel("Youtube");
        Channel c3 = new Channel("TV");
        Channel c4 = new Channel("News App");
        
        channelDirectory.addChannel(c1);
        channelDirectory.addChannel(c2);
        channelDirectory.addChannel(c3);
        channelDirectory.addChannel(c4);

        // Link markets to their channels
        m1.addChannel(c1);
        m1.addChannel(c2);

        m2.addChannel(c1);
        m2.addChannel(c3);

        m3.addChannel(c3);
        m3.addChannel(c4);
    }

    // ==============================================================
    //  2. Generate Random Suppliers
    // ==============================================================
    /**
     * Generates a list of random suppliers and adds them to the SupplierDirectory.
     *
     * @param count number of suppliers to create
     */
    public void generateRandomSuppliers(int count) {
        for (int i = 0; i < count; i++) {
            String supplierName = faker.company().name();
            String supplierId = "S " + faker.number().digits(4);
            Supplier s = supplierDirectory.addSupplier(supplierName,supplierId);
        }

    }

    // ==============================================================
    //  3. Generate Random Products
    // ==============================================================
    /**
     * Randomly adds products to each existing supplier.
     *
     * @param maxProducts maximum number of products per supplier
     */
    public void generateRandomProducts(int maxProducts) {
        for (Supplier s : supplierDirectory.getSuppliers()) {
            int numProducts = 1 + rand.nextInt(maxProducts);

            for (int i = 0; i < numProducts; i++) {
                String name = faker.commerce().productName();
                String id = "P " + faker.number().digits(5);
                double price = 10 + rand.nextInt(50);
                s.addProduct(name, id, price);
            }
        }
    }

    // ==============================================================
    //  4. Generate Random Customers
    // ==============================================================
    /**
     * Creates random customers using Faker's name generator.
     *
     * @param count number of customers to generate
     */
    public void generateRandomCustomers(int count) {
        for (int i = 0; i < count; i++) {
            String name = faker.name().fullName();
            String id = "C " + faker.number().digits(4);
            int age = 10 + rand.nextInt(90);
            String marketTag = "";
            if (age >= 10 && age <= 40) {
                marketTag = "Youth Market";
            }
            else if (age >= 41 && age <= 70) {
                marketTag = "Mid-Age Market";
            }
            else {
                marketTag = "Elder Market";
            }
            Customer c = new Customer(name, id, marketTag, age);
            customerDirectory.addCustomer(c);
            marketDirectory.assignCustomerByTag(c);
        }

    }

    // ==============================================================
    //  5. Generate Random Orders
    // ==============================================================
    /**
     * Creates random orders for each customer.
     * Each order will later be filled with random OrderItems.
     *
     * @param maxOrdersPerCustomer maximum number of orders each customer will have
     * @param maxItemsPerOrder  maximum number of order items allowed per order
     */
    public void generateRandomOrders(int maxOrdersPerCustomer, int maxItemsPerOrder) {
        int numOrders = 1 + rand.nextInt(maxOrdersPerCustomer);
        
        for (Customer c : customerDirectory.getCustomers()) {
            for (int i = 0; i < numOrders; i++) {
                String orderId = "O " + faker.number().digits(6);
                Order o = masterOrderList.createOrder(c,orderId);
                c.addOrder(o);

                generateRandomOrderItems(o, maxItemsPerOrder);
            }
        }

    }

    // ==============================================================
    //  6. Generate Random Order Items
    // ==============================================================
    /**
     * Adds random OrderItems to a given Order.
     * Randomly selects products from any supplier.
     *
     * @param order the order to populate
     * @param maxItems maximum number of order items to add
     */
    public void generateRandomOrderItems(Order order, int maxItems) {
        int numItems = 1 + rand.nextInt(maxItems);

        for (int j = 0; j < numItems; j++) {
            Product p = pickRandomProductFromAnySupplier();
            if (p == null) continue;            
            int qty = 1 + rand.nextInt(5);
            masterOrderList.addOrderItem(order, p, qty);
        }
    }

    // ==============================================================
    //  Helper: Random Product Selector
    // ==============================================================
    /**
     * Randomly selects one product from any supplier in the directory.
     * Returns null if there are no suppliers or no available products.
     */
    private Product pickRandomProductFromAnySupplier() {
        List<Supplier> suppliers = supplierDirectory.getSuppliers();
        if (suppliers.isEmpty()) return null;

        Supplier s = suppliers.get(rand.nextInt(suppliers.size()));
        ArrayList<Product> products = s.getProducts();
        if (products.isEmpty()) return null;

        return products.get(rand.nextInt(products.size()));
    }
    
    // ==============================================================
    //  7. High-level Mock Data Generator
    // ==============================================================
    /**
     * One-click method that builds a complete mock business dataset.
     *
     * @param numCustomers      number of customers
     * @param maxOrdersPerCustomer maximum orders per customer
     */
    public void generateMockBusinessData(int numCustomers,
                                         int maxOrdersPerCustomer) {
        generateRandomSuppliers(8);
        generateRandomProducts(15);
        generateRandomCustomers(numCustomers);
        generateRandomOrders(maxOrdersPerCustomer, 5);
        
    }

    /*
     * 8. Print Report By Market
     * Sort and print all markets in descending order of total revenue.
     * For each market:
     * Print the market name
     * Print the two channels used by this market
     * For each channel in that market, compute and print:
     * total number of orders (from customers belonging to this market and this channel)
     * total revenue for that channel within this market
     * Inside each market, the two channels must also be sorted in descending order of revenue
     */
    public void printReportByMarket() {
        System.out.println("=== Report by Market ===");
        
        ArrayList<Market> markets = new ArrayList<>();
        ArrayList<Double> marketRevenue = new ArrayList<>();
        List<ArrayList<Channel>> channelsList = new ArrayList<>();
        List<ArrayList<Integer>> orderCountsList = new ArrayList<>();
        List<ArrayList<Double>> totalRevenueList = new ArrayList<>();
        
        for (Market m : marketDirectory.getMarkets()) {
            double totalRevenue = 0.0;
            
            ArrayList<Channel> channels = new ArrayList<>();
            ArrayList<Integer> orderCounts = new ArrayList<>();
            ArrayList<Double> channelRevenue = new ArrayList<>();
            
            for (Channel ch : m.getChannels()) {
                int numOrders = 0;
                double revenue = 0.0;
                
                for (Customer c : ch.getCustomers()) {
                    if (c.getMarket() == m) {
                        numOrders += c.getOrderCount();
                        revenue += c.getCustomerOrderTotal();
                    }
                }
                totalRevenue += revenue;
                
                channels.add(ch);
                orderCounts.add(numOrders);
                channelRevenue.add(revenue);
            }
            
            if (channelRevenue.get(0) < channelRevenue.get(1)) {
                double tempD = channelRevenue.get(0);
                channelRevenue.set(0, channelRevenue.get(1));
                channelRevenue.set(1, tempD);
                
                int tempI = orderCounts.get(0);
                orderCounts.set(0, orderCounts.get(1));
                orderCounts.set(1, tempI);
                
                Channel tempC = channels.get(0);
                channels.set(0, channels.get(1));
                channels.set(1, tempC);
            }
            
            markets.add(m);
            marketRevenue.add(totalRevenue);
            channelsList.add(channels);
            orderCountsList.add(orderCounts);
            totalRevenueList.add(channelRevenue);
        }
        
        for (int i = 0; i < marketRevenue.size() - 1; i++) {
            for (int j = i + 1; j < marketRevenue.size(); j++) {
                if (marketRevenue.get(j) > marketRevenue.get(i)) {
                double tempR = marketRevenue.get(i);
                marketRevenue.set(i, marketRevenue.get(j));
                marketRevenue.set(j, tempR);
                
                Market tempM = markets.get(i);
                markets.set(i, markets.get(j));
                markets.set(j, tempM);
                
                ArrayList tempC = channelsList.get(i);
                channelsList.set(i, channelsList.get(j));
                channelsList.set(j, tempC);
                
                ArrayList tempO = orderCountsList.get(i);
                orderCountsList.set(i, orderCountsList.get(j));
                orderCountsList.set(j, tempO);
                
                ArrayList tempT = totalRevenueList.get(i);
                totalRevenueList.set(i, totalRevenueList.get(j));
                totalRevenueList.set(j, tempT);
                }
            }
        }
        
        for (int i = 0; i < markets.size(); i++) {
            String marketName = markets.get(i).getName();
            System.out.println(marketName);
            for (int j = 0; j < channelsList.get(i).size(); j++) {
                String channelName = channelsList.get(i).get(j).getName();
                System.out.println(" -" + channelName + " Channel");
                System.out.println("    Total number of orders from customers belonging to "
                        + marketName + " and " + channelName + " channel is: "
                        + orderCountsList.get(i).get(j));
                System.out.println("    Total revenue for " + channelName + " channel within "
                        + marketName + " is: $" + totalRevenueList.get(i).get(j));
            }
        }
        
        System.out.println("");
    }
    
//.
//.
    /*
     * 9. Print Report By Channel
     * Across all markets, for each channel:
     * Print the channel name.
     * Print:
     * total number of orders that went through this channel (all markets combined)
     * total revenue for this channel (all markets combined)
     * Sort all channels in descending order of revenue.
     */
    public void printReportByChannel() {
        System.out.println("=== Report by Channel ===");
        
        ArrayList<Channel> channels = new ArrayList<>();
        ArrayList<Integer> orderCounts = new ArrayList<>();
        ArrayList<Double> channelRevenue = new ArrayList<>();
        
        for (Channel ch: channelDirectory.getChannels()) {
            int numOrders = 0;
            double revenue = 0.0;
                
            for (Customer c : ch.getCustomers()) {
                numOrders += c.getOrderCount();
                revenue += c.getCustomerOrderTotal();
            }
            
            channels.add(ch);
            orderCounts.add(numOrders);
            channelRevenue.add(revenue);
        }
            
        if (channelRevenue.get(0) < channelRevenue.get(1)) {
            double tempD = channelRevenue.get(0);
            channelRevenue.set(0, channelRevenue.get(1));
            channelRevenue.set(1, tempD);
            
            int tempI = orderCounts.get(0);
            orderCounts.set(0, orderCounts.get(1));
            orderCounts.set(1, tempI);
                
            Channel tempC = channels.get(0);
            channels.set(0, channels.get(1));
            channels.set(1, tempC);
        }
        
        for (int i = 0; i < channels.size(); i++) {
            String channelName = channels.get(i).getName();
            System.out.println(channelName + " Channel");
            System.out.println("  Total number of orders that went through "
                    + channelName + " channel (all markets combined) is: "
                    + orderCounts.get(i));
            System.out.println("  Total revenue for "
                    + channelName + " channel (all markets combined) is: $"
                    + channelRevenue.get(i));
        }
    }
    
}
    
    
    

