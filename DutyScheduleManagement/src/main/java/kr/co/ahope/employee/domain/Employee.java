package kr.co.ahope.employee.domain;

public class Employee {
	private int emplId;
	private String name;
	private String email;

	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee(int emplId, String name, String email) {
		super();
		this.emplId = emplId;
		this.name = name;
		this.email = email;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [emplId=" + emplId + ", name=" + name + ", email=" + email + "]";
	}

}
