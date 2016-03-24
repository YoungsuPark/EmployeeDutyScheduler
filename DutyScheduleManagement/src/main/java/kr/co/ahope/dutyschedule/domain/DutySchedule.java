package kr.co.ahope.dutyschedule.domain;

public class DutySchedule {

	private int emplDutyScheduleId;
	private int emplId;
	private int year;
	private int month;
	private int date;
	private String name;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmplDutyScheduleId() {
		return emplDutyScheduleId;
	}

	public void setEmplDutyScheduleId(int emplDutyScheduleId) {
		this.emplDutyScheduleId = emplDutyScheduleId;
	}

	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public DutySchedule() {
		super();
	}

	public DutySchedule(int emplDutyScheduleId, int emplId, int year, int month, int date) {
		super();
		this.emplDutyScheduleId = emplDutyScheduleId;
		this.emplId = emplId;
		this.year = year;
		this.month = month;
		this.date = date;
	}

	@Override
	public String toString() {
		return "DutySchedule [emplDutyScheduleId=" + emplDutyScheduleId + ", emplId=" + emplId + ", year=" + year + ", month=" + month + ", date=" + date + "]";
	}

}
