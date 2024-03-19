package Project;
import java.util.ArrayList;
public class TeamLeader extends Developer{
	double bonus_rate;
	public TeamLeader(String empID, String empName, int baseSal, String teamName, ArrayList<String> programmingLanguages, int expYear, double bonus_rate) {
		super(empID, empName, baseSal, teamName, programmingLanguages, expYear);
		this.bonus_rate = bonus_rate;
	}
	public double getBonusRate() {
		return this.bonus_rate;
	}
	public double getSalary() {
		Developer dev = new Developer(empID, empName, baseSal, teamName, programmingLanguages, expYear);
		return dev.getSalary() + bonus_rate*baseSal;
	}

}
