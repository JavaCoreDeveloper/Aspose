package aspose.com;

public class Employee {
  private String fullName;
  private String email;
  private String  address;
  private double salary;
  
  
  public Employee(){
    fullName = "";
    email = "";
    address = "";
    salary = 0;
   }


  public String getFullName() {
    return fullName;
  }


  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public String getAddress() {
    return address;
  }


  public void setAddress(String address) {
    this.address = address;
  }


  public double getSalary() {
    return salary;
  }


  public void setSalary(double salary) {
    this.salary = salary;
  }
  
  

}
