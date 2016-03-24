package kr.co.ahope.employee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import kr.co.ahope.employee.domain.Employee;

@Repository(value = "emplMapper")
public interface EmplMapper {
	
	@Results({
        @Result(property = "emplId", column = "empl_id"),
        @Result(property = "name", column = "name"),
        @Result(property = "email", column = "email")
      })
	
	@Select("SELECT * FROM employees")
	List<Employee> select();
	
	@Insert("INSERT INTO employees (name, email) VALUES (#{name}, #{email})")
	void insert(Employee empl);

	@Update("UPDATE employees SET name = #{name}, email = #{email} WHERE empl_id = #{emplId}")
	void update(Employee empl);

	@Delete("DELETE FROM employees WHERE empl_id = #{emplId}")
	void delete(int emplId);
}
