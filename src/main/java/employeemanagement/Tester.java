package employeemanagement;


public class Tester extends Employee {
	double bonusRate;
	String type;
	public Tester(String empID, String empName, int baseSal, double bonusRate, String type) {
		super(empID, empName, baseSal);
		this.bonusRate = bonusRate;
		this.type = type;
	}
	public double getBonusRate() {
		return this.bonusRate;
	}
	public String getType() {
		return this.type;
	}
	public double getSalary() {
		return baseSal + bonusRate * baseSal;
	}

}
