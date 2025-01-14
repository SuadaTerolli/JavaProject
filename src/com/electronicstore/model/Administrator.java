package com.electronicstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Supplier;

public class Administrator extends User {
    private ArrayList<User> employees;

    public Administrator(int id, String name, String username, Date date_of_birth, int phoneNumber, String email, int salary, String password, ArrayList<User> employees)
    {
        super(id,name,username,date_of_birth,phoneNumber,email,salary,password);
        this.employees=employees;
    }
    public ArrayList<User> getEmployees()
    {
        return employees;
    }
    public void registerEmployee(User user)
    {
        if (user!=null)
        {
            for (User existingUser:employees)
            {
                if (existingUser.getId()== user.getId())
                {
                    System.out.println("User with ID " + user.getId() + " already exists!");
                    return;
                }
                employees.add(user);
                System.out.println("Employee " + user.getName() + " is registered successfuly!");
            }//THESE PRINTINGS ARE OPTIONAL
        }
        else
        {
            System.out.println("Invalid user entered!");
        }
    }
    public User editEmployee(int userId,String name,int phoneNumber,String email,int salary,String newRole)
    {
        for (User employee:employees)
        {
            if (employee.getId()==userId)
            {
                if (name!=null&& !name.isEmpty())
                {
                    employee.setName(name);
                }
                if (phoneNumber!=0)
                {
                    employee.setPhoneNumber(phoneNumber);
                }
                if (email!=null && !email.isEmpty())
                {
                    employee.setEmail(email);
                }
                if (salary!=0)
                {
                    employee.setSalary(salary);
                }
                if (newRole!=null && !newRole.isEmpty())
                {
                    User updatedUser=changeRoles(employee,newRole);
                    if (updatedUser!=null)
                    {
                        employees.set(employees.indexOf(employee),updatedUser);
                        return updatedUser;
                    }
                    else
                    {
                        System.out.println("Invalid role input!");//OPTIONAL OUTPUT
                    }
                }
                return employee;
            }
        }
        System.out.println("Employee not found!");
        return null;
    }

    public void deleteEmployee(int id)
    {
        boolean removed = employees.removeIf(employee -> employee.getId() == id);
        if (removed) {
            System.out.println("Employee with ID " + id + " removed.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
    public int generateFinancialSummary(Date startDate, Date endDate)//CHECK THIS OUT IMMEDIATELY
    {
        double totalIncome=0.0;
        double totalCost=0.0;

        for (User employee:employees)
        {
            if (employee instanceof Cashier)
            {
                ArrayList<Bill> bills=((Cashier)employee).viewAllBills();
                for (Bill bill:bills)
                {
                    if (bill.getBillDate().after(startDate)&&bill.getBillDate().before(endDate))
                    {
                        totalIncome+=bill.calculateTotalAmount();
                    }
                }
            }

        }
        totalCost=0;
        FinancialSummary summary=new FinancialSummary(startDate,endDate,totalIncome);
        return 0;
    }

    public User changeRoles(User user,String newRole)
    {
        switch (newRole.toLowerCase())
        {
            /*
            case "administrator":
                return new Administrator(
                        user.getId(),user.getName(),user.getUsername(),
                        user.getDate_of_birth(),user.getPhoneNumber(),user.getEmail(),
                        user.getSalary(),new ArrayList<User>()
                );
            case "manager":
                return new Manager(
                        user.getId(),user.getName(),user.getUsername(),
                        user.getDate_of_birth(),user.getPhoneNumber(),user.getEmail(),
                        user.getSalary(),new ArrayList<Sector>(),new ArrayList<Supplier>()
                );
            case "cashier":
                return new Cashier(
                        user.getId(),user.getName(),user.getUsername(),
                        user.getDate_of_birth(),user.getPhoneNumber(),user.getEmail(),
                        user.getSalary(),user.getSector()
                );*/
            default:
                return null;
        }
    }
}
