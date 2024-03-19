
package Project;

import java.util.*;
import java.io.*;

public class CompanyManagement {

	private ArrayList<Employee> empList;

	// Parameters:
	// path: path of ListOfEmployees.txt
	// path1: path of PLInfo.txt
	public CompanyManagement(String path, String path1) throws FileNotFoundException {
		empList = getEmployeeFromFile(path, path1);
	}

	public ArrayList<Employee> getEmployeeFromFile(String path, String path1) throws FileNotFoundException {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		ArrayList<String> pltempList = new ArrayList<String>();
		File file = new File(path);
		Scanner sc = new Scanner(file);
		File file1 = new File(path1);
		Scanner sc1 = new Scanner(file1);
		while (sc1.hasNextLine()) { // PLInfo
			pltempList.add(sc1.nextLine());
		}
		ArrayList<String> prgmLanguages = new ArrayList<String>();
		while (sc.hasNextLine()) { // ListOfEmployee
			String[] empInfo = sc.nextLine().split(",");
			String empID = empInfo[1];
			for (String t : pltempList) {
				String prgmID = t.split(",")[0];
				if (empID.equals(prgmID)) {
					List<String> list = Arrays.asList(t.split(","));
					prgmLanguages = new ArrayList<String>(list);
					prgmLanguages.remove(0); // remove ID from prgmlanguages
				}
			}
			// DEVELOPER ヽ( ᐛ )ﾉ
			if (empInfo.length == 6 && empID.charAt(0) == 'D') {
				int sal = Integer.parseInt(empInfo[5]);
				int expYear = Integer.parseInt(empInfo[4]);
				Developer dev = new Developer(empID, empInfo[2], sal, empInfo[3], prgmLanguages, expYear);
				empList.add(dev);
			}
			// TEAM LEADER ヽ( ᐛ )ﾉ
			else if (empInfo.length == 8 && empInfo[5].equals("L")) {
				int sal = Integer.parseInt(empInfo[7]);
				double bonus = Double.parseDouble(empInfo[6]);
				int expYear = Integer.parseInt(empInfo[4]);
				TeamLeader leader = new TeamLeader(empID, empInfo[2], sal, empInfo[3], prgmLanguages, expYear, bonus);
				empList.add(leader);
			}
			// TESTER ヽ( ᐛ )ﾉ
			else {
				int sal = Integer.parseInt(empInfo[5]);
				double bonus = Double.parseDouble(empInfo[3]);
				Tester tester = new Tester(empID, empInfo[2], sal, bonus, empInfo[4]);
				empList.add(tester);
			}
		}
		sc.close();
		sc1.close();
		return empList;
	}

	public ArrayList<Developer> getDeveloperByProgrammingLanguage(String pl) {
		ArrayList<Developer> devList = new ArrayList<Developer>();
		for (Employee emp : empList) {
			if (emp instanceof Developer dev) {
				if (dev.getProgrammingLanguages().contains(pl)) {
					devList.add(dev);
				}
			}
		}
		return devList;
	}

	public ArrayList<Tester> getTestersHaveSalaryGreaterThan(double value) {
		ArrayList<Tester> testList = new ArrayList<Tester>();
		for (Employee emp : empList) {
			if (emp instanceof Tester tester) {
				if (tester.getSalary() > value) {
					testList.add(tester);
				}
			}
		}
		return testList;
	}

	public Employee getEmployeeWithHigestSalary() {
		Double highest = empList.get(0).getSalary();
		int index = 0;
		for (int i = 0; i < empList.size() - 1; i++) {
			if (empList.get(i).getSalary() >= highest) {
				highest = empList.get(i).getSalary();
				index = i;
			}
		}
		return empList.get(index);
	}

	public TeamLeader getLeaderWithMostEmployees() {
		TeamLeader leaderRes = null;
		ArrayList<TeamLeader> leaderAL = new ArrayList<TeamLeader>();
		ArrayList<Developer> devAL = new ArrayList<Developer>();
		for(Employee emp:empList) {
			if(emp instanceof TeamLeader lead) {
				leaderAL.add(lead);
			}
			if(emp instanceof Developer dev) {
				devAL.add(dev);
			}
		}
		//Count Employee
		int most = 0, employee = 0;
		for(TeamLeader lead:leaderAL) {
			for(Developer dev:devAL) {
				if(lead.teamName.equals(dev.teamName)) {
					employee++;
				}
			}
			if(employee>=most) {
				most = employee;
			}
			employee=0;
		}
		for(TeamLeader lead:leaderAL) {
			for(Developer dev:devAL) {
				if(lead.teamName.equals(dev.teamName)) {
					employee++;
				}
			}
			if(most==employee) {
				leaderRes = lead;
			}
		}
		return leaderRes;
	}

	// Print empList
	public void printEmpList() {
		for (Employee tmp : empList) {
			System.out.println(tmp);
		}
	}

	// Methods for writing file
	public <T> void writeFile(String path, ArrayList<T> list) {
		try {
			FileWriter writer = new FileWriter(path);
			for (T tmp : list) {
				writer.write(tmp.toString());
				writer.write("\r\n");
			}
			writer.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("Error.");
		}
	}

	public <T> void writeFile(String path, T object) {
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(object.toString());
			writer.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("Error.");
		}
	}

}
