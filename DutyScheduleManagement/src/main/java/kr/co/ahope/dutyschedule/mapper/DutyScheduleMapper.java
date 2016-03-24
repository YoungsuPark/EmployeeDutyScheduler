package kr.co.ahope.dutyschedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import kr.co.ahope.dutyschedule.domain.DutySchedule;

@Repository(value="dsMapper")
public interface DutyScheduleMapper {

	@Results({
		@Result(property = "emplDutyScheduleId", column = "empl_duty_schedule_id"),
		@Result(property = "emplId", column = "empl_id"),
		@Result(property = "year", column = "year"),
		@Result(property = "month", column = "month"),
		@Result(property = "date", column = "date")
	})

	@Select("SELECT * FROM employees INNER JOIN duty_schedules ON duty_schedules.empl_id = employees.empl_id WHERE month = #{month} AND year = #{year}")
	List<DutySchedule> select(@Param("month") int month, @Param("year")int year);
	
	@Select("SELECT * FROM employees INNER JOIN duty_schedules ON duty_schedules.empl_id = employees.empl_id WHERE date = #{date} AND month = #{month} AND year = #{year}")
	DutySchedule selectOne(@Param("date") int date, @Param("month") int month, @Param("year") int year);
	
	@Insert("INSERT INTO duty_schedules (empl_id, year, month, date) VALUES (#{emplId}, #{year}, #{month}, #{date})")
	void insert(DutySchedule dutySchedule);

	@Update("UPDATE duty_schedules SET empl_id = #{emplId} WHERE year = #{year} AND month = #{month} AND date = #{date}")
	void update(DutySchedule dutySchedule);
	
}