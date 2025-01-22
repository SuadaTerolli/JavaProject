package com.electronicstore.controller;

import com.electronicstore.model.Bill;
import com.electronicstore.model.User;
import com.electronicstore.view.CashierCreateBillView;
import com.electronicstore.view.LogIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CashierMainController {
    private User loggedInUser;
    private ObservableList<Bill> allBills;

    public CashierMainController(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.allBills = loadBillsFromFile("src/main/resources/files/bills.csv");
    }

    public ObservableList<Bill> getAllBills() {
        return allBills;
    }

    public ObservableList<Bill> getBillsFromToday() {
        ObservableList<Bill> filteredBills = FXCollections.observableArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        for (Bill bill : allBills) {
            if (dateFormat.format(bill.getBillDate()).equals(dateFormat.format(today))) {
                filteredBills.add(bill);
            }
        }
        return filteredBills;
    }

    public void handleCreateBill(Stage primaryStage) {
        try {
            new CashierCreateBillView(loggedInUser).start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleLogout(Stage primaryStage) {
        try {
            new LogIn().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSectorForUser(User user) {
        String filePath = "src/main/resources/files/user.csv";
        String sectorName = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header row
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 10) {
                    String username = parts[2].trim();
                    String sectorNameValue = parts[9].trim();
                    if (username.equals(user.getUsername())) {
                        sectorName = sectorNameValue.isEmpty() ? null : sectorNameValue;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sectorName;
    }

    private ObservableList<Bill> loadBillsFromFile(String filePath) {
        ObservableList<Bill> bills = FXCollections.observableArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                int billId = Integer.parseInt(fields[0].trim());
                String billNumber = fields[1].trim();
                Date billDate = dateFormat.parse(fields[2].trim());
                double totalAmount = Double.parseDouble(fields[3].trim());
                int totalQuantity = Integer.parseInt(fields[4].trim());
                bills.add(new Bill(billId, billNumber, billDate, totalQuantity, totalAmount));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return bills;
    }
}

