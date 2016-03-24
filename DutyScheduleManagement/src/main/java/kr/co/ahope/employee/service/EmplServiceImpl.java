package kr.co.ahope.employee.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.ahope.employee.domain.Employee;
import kr.co.ahope.employee.mapper.EmplMapper;

@Service(value = "emplService")
public class EmplServiceImpl implements EmplService {

	@Resource(name = "emplMapper")
	private EmplMapper emplMapper;
	
	@Override
	public List<Employee> getEmplList() {
		return emplMapper.select();
	}
	
	@Override
	public void addEmpl(Employee empl) {
		emplMapper.insert(empl);
	}
	
	@Override
	public void modifyEmplInfo(Employee empl) {
		emplMapper.update(empl);
	}
	
	@Override
	public void deleteEmpl(int emplId) {
		emplMapper.delete(emplId);
	}
	
	@Override
	public Employee getMailInfo(String email) {
		return emplMapper.selectOne(email);
	}
}
