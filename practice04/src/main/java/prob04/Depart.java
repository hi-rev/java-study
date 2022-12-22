package prob04;

public class Depart extends Employee {
	private String department;
	
	public Depart(String name, int salary, String department) { // 생성자
		super.setName(name);
		super.setSalary(salary);
		this.setDepartment(department);
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void getInformation() {
		super.getInformation();
		System.out.println("	부서: " + getDepartment());
	}
}
