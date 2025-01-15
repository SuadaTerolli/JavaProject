package com.electronicstore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class FinancialSummary implements Serializable {
    private double totalIncome;
    private double totalCosts;
    private double salaries;
    private double itemsPurchasedPrice;
    private Date startDate;
    private Date endDate;
    private ArrayList<User> users;
    private ArrayList<Item> items;
    private ArrayList<Bill> bills;

    public FinancialSummary()
    {

    }
    public FinancialSummary(Date startDate, Date endDate, double totalIncome)
    {
        this.startDate=startDate;
        this.endDate=endDate;
        this.totalIncome=totalIncome;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalCosts() {
        return totalCosts;
    }

    public double getSalaries() {
        return salaries;
    }

    public double getItemsPurchasedPrice() {
        return itemsPurchasedPrice;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public double viewTotalCosts()
    {
        for (User singleUser:users)
        {

            totalCosts+=singleUser.getSalary();
        }
        for (Item item:items)
        {
            totalCosts+=(item.getPurchasePrice()*item.getQuantity());
        }
        return totalCosts;
    }

    public double viewTotalIncome()
    {
        for (Bill bill:bills)
        {
            totalIncome+=bill.calculateTotalAmount();
        }
        return totalIncome-viewTotalCosts();
    }
    public double viewSalaries()
    {
        for (User singleUser:users)
        {
            salaries+=singleUser.getSalary();
        }
        return salaries;
    }
    public double viewItemsPurchasedPrice()
    {
        for (Item item:items)
        {
            itemsPurchasedPrice+=item.getPurchasePrice();
        }
        return itemsPurchasedPrice;
    }
    /*AM I SUPPOSED TO WRITE THE FOLLOWING ON THE MAIN FUNCTION ?*/
    /*FinancialSummary summary = new FinancialSummary(100000.50, 50000.75, 50000.75);
        CashierPerformance performance = new CashierPerformance("John Doe", 150, 7500.50);

        try {
            // Write binary files
            BinaryFileWriter.writeToBinaryFile("resources/data/binary/financial_summary.dat", summary);
            BinaryFileWriter.writeToBinaryFile("resources/data/binary/cashier_performance.dat", performance);
            System.out.println("Binary files written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    /*THIS ONE TOO???*/
    /* try {
            // Read binary files
            FinancialSummary summary = BinaryFileReader.readFromBinaryFile("resources/data/binary/financial_summary.dat");
            CashierPerformance performance = BinaryFileReader.readFromBinaryFile("resources/data/binary/cashier_performance.dat");

            System.out.println("Financial Summary: " + summary);
            System.out.println("Cashier Performance: " + performance);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
}
