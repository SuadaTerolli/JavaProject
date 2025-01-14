package sales.users;
import java.util.Date;

public class User {
    private int id;
    private String name;
    private String username;
    private Date date_of_birth;
    private int phoneNumber;
    private String email;
    private int salary;
    private String password;
    private static boolean isLoggedIn=false;

    public User(){

    }
    public User(int id,String name,String username,Date date_of_birth,int phoneNumber,String email,int salary,String password)
    {
        this.id=id;
        this.name=name;
        this.username=username;
        this.date_of_birth=new Date();
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.salary=salary;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    public boolean login(String username,String password)
    {
        if (username==null && password==null)
        {
            System.out.println("Username and password cannot be empty!");
            return false;
        }
        if (username.equals(validUsername)&&password.equals(validPassword))
        {
            isLoggedIn=true;
            System.out.println("Login successful"); //This is optional
            return true;
        }
        else
        {
            System.out.println("Invalid username or password");
            return false;
        }
    }
    public void logout()
    {
        if (isLoggedIn)
        {
            isLoggedIn=false;
            System.out.println("You are logged out!"); //This is optional
        }
    }
    //Do we really need a getUser() method when we have the toString()?
    public String toString()
    {
        return "id: "+getId()+" name: "+getName()+" username: "+getUsername()+" date of birth: "+getDate_of_birth()
                +" phone number: "+getPhoneNumber()+" email: "+getEmail()+" salary: "+getSalary();
    }
}
