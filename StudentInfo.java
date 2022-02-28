import java.util.Date;

public class StudentInfo {
	public class java {

	}

	private String name;
	private int avg;
		

	public StudentInfo(String n, int a) {
		name = n;
		avg = a;

	}
	
	public double getAvg() {
		return avg;
	}
	
	public String getName() {
		return name;
	}
	
	
	
	@Override
	public String toString() {
		return name + ": " + avg;
	}
	
	
}
