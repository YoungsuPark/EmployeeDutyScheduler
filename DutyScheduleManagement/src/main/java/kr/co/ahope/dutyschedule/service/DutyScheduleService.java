package kr.co.ahope.dutyschedule.service;

import java.util.Map;

import kr.co.ahope.dutyschedule.domain.MultiDutySchedule;

public interface DutyScheduleService {

	void addDutySchedule(MultiDutySchedule dutySchedules);

	void modifyDutySchedule(MultiDutySchedule dutySchedules);

	Map<String, Object> getDutySchedules(int month, int year);

	Map<String, Object> getInfoForDutyScheduleForm(int month, int year);

}
