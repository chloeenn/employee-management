package employeemanagement;

public abstract class Employee {

    protected String empID;
    protected String empName;
    protected int baseSal;

    public Employee(String empID, String empName, int baseSal){
        this.empID = empID;
        this.empName = empName;
        this.baseSal = baseSal;
    }
    
    public String getEmpID(){
        return this.empID;
    }

    public String getEmpName(){
        return this.empName;
    }

    public int getBaseSal(){
        return this.baseSal;
    }

    public abstract double getSalary();

    public String toString(){
        return this.empID + "_" + this.empName + "_" + this.baseSal;
    }

}