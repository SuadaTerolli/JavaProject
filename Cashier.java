import java.util.ArrayList;
import java.util.Date;

public class Cashier extends User{
    private Sector sector;

    public Cashier(int id, String name, String username, Date date_of_birth, int phoneNumber, String email, int salary, String password,Sector sector)
    {
        super(id,name,username,date_of_birth,phoneNumber,email,salary,password);
        this.sector=sector;
    }

    public Sector getSector() {
        return sector;
    }
    public ArrayList<Bill> viewBillsByDate()
    {
        ArrayList<Bill> todayBills=new ArrayList<Bill>();
        Date today=new Date();
        for (Bill bill:bills)
        {
            if (isSameDay(bill.getDateTime(),today))//MAYBE THIS NEEDS TO BE CHAGED TO getDate() only
            {
                todayBills.add(bill);
            }
        }
        return todayBills;
    }
    public boolean isSameDay(Date date1,Date date2)
    {
        return (date1.getYear()==date2.getYear()
        &&date1.getMonth()==date2.getMonth()
        &&date1.getDay()==date2.getDay());
    }
    public ArrayList<Bill> viewAllBills()
    {
        return new ArrayList<>(bills);//CHECK THIS OUT
    }
}
