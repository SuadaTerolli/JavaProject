import jdk.jfr.Category;

import java.util.ArrayList;
import java.util.Date;

public class Manager extends User{
    private ArrayList<Sector> sectors;
    private ArrayList<Supplier> suppliers;

    public Manager(int id, String name, String username, Date date_of_birth, int phoneNumber, String email, int salary, String password, ArrayList<Sector> sector,ArrayList<Supplier> suppliers)
    {
        super(id,name,username,date_of_birth,phoneNumber,email,salary,password);
        this.sectors=sectors;
        this.suppliers=suppliers;
    }

    public ArrayList<Sector> getSectors() {
        return sectors;
    }

    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }
    public void addItemToStock(Item item,int sectorId)
    {
        Sector selectedSector=sectors.get(sectorId);
        selectedSector.addItem(item);//MAYBE WE NEED TO ADD A NEW METHOD ON THE SECTOR CLASS
    }
    public Categor addCategory(Categor category)//CHECK OUT AGAIN
    {
        Categor categoryNew= new Categor(category);
    }
    
}
