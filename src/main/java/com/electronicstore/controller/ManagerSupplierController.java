package com.electronicstore.controller;

import com.electronicstore.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ManagerSupplierController {

    public List<Category> loadCategories(String filePath) {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                categories.add(new Category(
                        Integer.parseInt(fields[0].trim()),
                        fields[1].trim()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Item> loadItems(String filePath, List<Category> categories, List<Supplier> suppliers) {
        List<Item> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                int itemId = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                String categoryName = fields[2].trim();
                String supplierName = fields[3].trim();

                Category category = categories.stream()
                        .filter(c -> c.getName().equalsIgnoreCase(categoryName))
                        .findFirst()
                        .orElse(null);

                Supplier supplier = suppliers.stream()
                        .filter(s -> s.getName().equalsIgnoreCase(supplierName))
                        .findFirst()
                        .orElse(null);

                if (category == null || supplier == null) {
                    continue;
                }

                double purchasePrice = Double.parseDouble(fields[5].trim());
                double sellingPrice = Double.parseDouble(fields[6].trim());
                int quantity = Integer.parseInt(fields[7].trim());

                items.add(new Item(itemId, name, category, supplier, null, purchasePrice, sellingPrice, quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Map<String, Integer> loadSectors(String filePath) {
        Map<String, Integer> sectors = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                sectors.put(fields[1].trim(), Integer.parseInt(fields[0].trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sectors;
    }

    public ObservableList<com.electronicstore.view.ManagerSuppliersView.SupplierEntry> filterEntriesBySector(List<Item> items, Map<String, Integer> sectors, String sectorName) {
        if (sectorName == null || !sectors.containsKey(sectorName)) {
            return FXCollections.observableArrayList();
        }

        int sectorId = sectors.get(sectorName);
        return FXCollections.observableArrayList(
                items.stream()
                        .filter(item -> item.getCategory() != null && item.getCategory().getCategoryId() == sectorId)
                        .map(item -> new com.electronicstore.view.ManagerSuppliersView.SupplierEntry(
                                item.getSupplier().getName(),
                                item.getName(),
                                item.getCategory().getName(),
                                item.getPurchasePrice()
                        ))
                        .collect(Collectors.toList())
        );
    }
}
