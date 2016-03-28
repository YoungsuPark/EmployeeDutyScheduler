package kr.co.ahope.employee.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ahope.employee.domain.Employee;
import kr.co.ahope.employee.mapper.EmplMapper;

@Transactional
@Service(value = "emplService")
public class EmplServiceImpl implements EmplService {

	@Resource(name = "emplMapper")
	private EmplMapper emplMapper;

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
	@Override
	public Employee findOne(String email) {
		return emplMapper.findByMail(email);
	}

	@Transactional(readOnly = true)
	@Override
	public Employee findOne(int emplId) {
		return emplMapper.findByEmplId(emplId);
	}

}