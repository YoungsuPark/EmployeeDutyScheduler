package kr.co.ahope.dutyschedule.domain;

import java.util.List;

public class MultiDutySchedule {
	
	private List<DutySchedule> dutySchedules;

	public List<DutySchedule> getDutySchedules() {
		return dutySchedules;
	}

	public void setDutySchedules(List<DutySchedule> dutySchedules) {
		this.dutySchedules = dutySchedules;
	}

	public MultiDutySchedule() {
		super();
	}

	public MultiDutySchedule(List<DutySchedule> dutySchedules) {
		super();
		this.dutySchedules = dutySchedules;
	}

	@Override
	public String toString() {
		return "MultiDutySchedule [multiDutySchedule=" + dutySchedules + "]";
	}

}