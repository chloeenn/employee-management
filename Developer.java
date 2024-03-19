package Project;

import java.util.ArrayList;

public class Developer extends Employee {
	public String teamName;
	public ArrayList<String> programmingLanguages;
	public int expYear;

	public Developer(String empID, String empName, int sal, String teamName, ArrayList<String> programmingLanguages,
			int expYear) {
		super(empID, empName, sal);
		this.teamName = teamName;
		this.programmingLanguages = programmingLanguages;
		this.expYear = expYear;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public ArrayList<String> getProgrammingLanguages() {
		return this.programmingLanguages;
	}

	public int getExpYear() {
		return this.expYear;
	}

	public double getSalary() {
		int exp = getExpYear();
		int base = getBaseSal();
		double sal = 0;
		if (exp >= 5) {
			sal = base + exp * 2000000;
		} else if (exp >= 3&&exp < 5 ) {
			sal = base + exp * 1000000;
		} else {
			sal = base;	
		}
		return sal;
	}

	public String toString() {
		return empID + "_" + empName + "_" + baseSal + "_" + teamName + "_" + programmingLanguages + "_" + expYear;
	}

}
