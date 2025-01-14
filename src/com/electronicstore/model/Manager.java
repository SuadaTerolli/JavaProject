package com.electronicstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Manager extends User{
    private ArrayList<Sector> sectors;
    private ArrayList<Supplier> suppliers;
    private ArrayList<Category> categories;

    public Manager(int id, String name, String username, Date date_of_birth, int phoneNumber, String email, int salary, String password, ArrayList<Sector> sector,ArrayList<Supplier> suppliers,ArrayList<Category>categories)
    {
        super(id,name,username,date_of_birth,phoneNumber,email,salary,password);
        this.sectors=sectors;
        this.suppliers=suppliers;
        this.categories=categories;
    }

    public ArrayList<Sector> getSectors() {
        return sectors;
    }

    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }
    public ArrayList<Category>getCategories()
    {
        return categories;
    }
    public void addItemToStock(Item item,int sectorId)
    {
        Sector selectedSector=sectors.get(sectorId);
        //selectedSector.addItem(item);//MAYBE WE NEED TO ADD A NEW METHOD ON THE SECTOR CLASS
        //OR MAYBE WE NEED TO CONNECT THIS TO THE DATABASE
    }
    public Category addCategory(String categoryName)//CHECK OUT AGAIN
    {
        UUID categoryId=UUID.randomUUID();
        long combinedBits = categoryId.getMostSignificantBits();
        int part1 = (int) ((combinedBits >> 32)& 0xFFFFFFFF);  // Top 32 bits
        Category categoryNew= new Category(part1,categoryName);
        return categoryNew;//DATABASE
    }

}
