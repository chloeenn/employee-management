package employeemanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class CompanyManagementTest {

    private CompanyManagement company;

    @BeforeEach
    void setUp() throws Exception {
        // Create sample data files
        try (FileWriter writer = new FileWriter("ListOfEmployees.txt")) {
            writer.write("1,D01,John,TeamA,5,60000\n"); // Developer
            writer.write("2,L01,Sarah,TeamA,10,L,5000,90000\n"); // TeamLeader
            writer.write("3,T01,Alice,2000.0,QA,40000\n"); // Tester
        }

        try (FileWriter writer = new FileWriter("PLInfo.txt")) {
            writer.write("D01,Java,Python\n");
            writer.write("L01,Java,C++\n");
        }

        company = new CompanyManagement("ListOfEmployees.txt", "PLInfo.txt");
    }

    @Test
    void testGetDeveloperByProgrammingLanguage() {
        ArrayList<Developer> javaDevs = company.getDeveloperByProgrammingLanguage("Java");
        assertEquals(1, javaDevs.size());
        assertEquals("D01", javaDevs.get(0).getEmpID());
    }

    @Test
    void testGetTestersHaveSalaryGreaterThan() {
        ArrayList<Tester> testers = company.getTestersHaveSalaryGreaterThan(30000);
        assertEquals(1, testers.size());
        assertEquals("T01", testers.get(0).getEmpID());
    }

    @Test
    void testGetEmployeeWithHigestSalary() {
        Employee highest = company.getEmployeeWithHigestSalary();
        assertEquals("L01", highest.getEmpID());
    }

    @Test
    void testGetLeaderWithMostEmployees() {
        TeamLeader leader = company.getLeaderWithMostEmployees();
        assertEquals("L01", leader.getEmpID());
    }

    @Test
    void testWriteFileSingleObject() {
        company.writeFile("single_output.txt", new Developer("D99", "Bob", 80000, "TeamX", new ArrayList<>(), 5));
    }

    @Test
    void testWriteFileList() {
        ArrayList<Tester> testers = company.getTestersHaveSalaryGreaterThan(0);
        company.writeFile("tester_list.txt", testers);
    }
}
