package kr.co.ahope.employee.service;

import java.util.List;

import kr.co.ahope.employee.domain.Employee;

public interface EmplService {

	void addEmpl(Employee empl);

	List<Employee> getEmplList();

	void modifyEmplInfo(Employee empl);

	void deleteEmpl(int emplId);

	Employee getMailInfo(String email);

}
