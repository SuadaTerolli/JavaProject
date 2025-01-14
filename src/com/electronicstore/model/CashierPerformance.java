package com.electronicstore.model;

public class CashierPerformance {
    private int totalBills;
    private int itemsSold;
    private double totalRevenue;

    // Getters and Setters
    public int getTotalBills() { return totalBills; }
    public void setTotalBills(int totalBills) { this.totalBills = totalBills; }
    public int getItemsSold() { return itemsSold; }
    public void setItemsSold(int itemsSold) { this.itemsSold = itemsSold; }
    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }

    // Static Method to Calculate Performance
    public static CashierPerformance calculatePerformance(String cashierName, String timePeriod) {
        CashierPerformance performance = new CashierPerformance();

        // Replace with SQLite queries using your database integration
        // Example pseudo-code:
        // String query = "SELECT COUNT(bill_id), SUM(items_sold), SUM(revenue) FROM Bills WHERE cashier_name = ? AND time_period = ?";
        // PreparedStatement statement = connection.prepareStatement(query);
        // ResultSet result = statement.executeQuery();
        // if (result.next()) { performance.setTotalBills(result.getInt(1)); performance.setItemsSold(result.getInt(2)); performance.setTotalRevenue(result.getDouble(3)); }

        return performance;
    }
}