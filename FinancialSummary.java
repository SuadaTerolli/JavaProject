package Reports;
public class FinancialSummary implements Reportable {
    private double totalIncome;
    private double totalCosts;
    public FinancialSummary()
    {

    }
    public FinancialSummary(double totalIncome, double totalCosts)
    {
        this.totalIncome=totalIncome;
        this.totalCosts=totalCosts;
    }
    public double getTotalIncome()
    {
        return totalIncome; 
    }
    public double getTotalCosts()
    {
        return totalCosts;
    }
}
