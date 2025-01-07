package sales;
import java.util.Date;
import java.util.ArrayList;
public class Bill {
    private int billId;
    private String billNumber;
    private Date billDate;
    private ArrayList<BillItem> billItems;
    private double totalAmount;
    private int sectorId;

    public Bill() {
        
    }//ALESIA_COMMENT
    public Bill(int billId, String billNumber, Date billDate, ArrayList<BillItem> billItems, double totalAmount, int sectorId) {
        this.billId = billId;
        this.billNumber = billNumber;
        this.billDate = billDate;
        this.billItems = billItems;
        this.totalAmount = totalAmount;
        this.sectorId = sectorId;
    }
    public double calculateTotalAmount() {
        double total = 0.0;
        for (BillItem billItem : billItems) {
            total += billItem.getItem().getSellingPrice() * billItem.getItemQuantity();
        }
        this.totalAmount = total;
        return total;
    }
    public void printBill() {
        System.out.println("Bill ID: " + billId);
        System.out.println("Bill Number: " + billNumber);
        System.out.println("Bill Date: " + billDate);
        System.out.println("Sector ID: " + sectorId);
        System.out.println("Items:");
        for (BillItem billItem : billItems) {
            System.out.println("- " + billItem.getItem().getName() + " Quantity: " + billItem.getItemQuantity());
        }
        System.out.println("Total Amount: " + totalAmount);
    }
    public ArrayList<Bill> getBills(int sectorId, Date startDate, Date endDate) {
        ArrayList<Bill> bills = new ArrayList<>();
        if (this.sectorId == sectorId && billDate.compareTo(startDate) >= 0 && billDate.compareTo(endDate) <= 0) {
            bills.add(this);
        }
        return bills;
    }
    public void setBillItems(ArrayList<BillItem> billItems) {
        this.billItems = billItems;
    }

    public int getBillId() {
        return billId;
    }

}
