package Reports;
public class PerformanceReport implements Reportable{
    private int totalBills;
    private double totalRevenue;
    private int ItemsSold;

    public PerformanceReport()
    {

    }
    public PerformanceReport(int totalBills, double totalRevenue, int ItemsSold)
    {
        this.totalBills=totalBills;
        this.totalRevenue=totalRevenue;
        this.ItemsSold=ItemsSold;
    }
    public int getTotalBills(Cashier cashier)
    {
        return totalBills;
    }
    public double getTotalRevenue(Cashier cashier)
    {
        return totalRevenue;
    }
    public int getItemsSold()
    {
        return ItemsSold;
    }


}