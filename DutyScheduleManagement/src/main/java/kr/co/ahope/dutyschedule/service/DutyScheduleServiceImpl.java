package kr.co.ahope.dutyschedule.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ahope.common.utils.CalendarUtil;
import kr.co.ahope.common.utils.MailUtil;
import kr.co.ahope.dutyschedule.domain.DutySchedule;
import kr.co.ahope.dutyschedule.domain.MultiDutySchedule;
import kr.co.ahope.dutyschedule.mapper.DutyScheduleMapper;
import kr.co.ahope.employee.controller.EmplController;
import kr.co.ahope.employee.domain.Employee;
import kr.co.ahope.employee.service.EmplService;

@Service(value = "dsService")
public class DutyScheduleServiceImpl implements DutyScheduleService {

	private static final Logger logger = LoggerFactory.getLogger(EmplController.class);

	@Resource(name="emplService")
	private EmplService emplService;	
	
	@Resource(name = "dsMapper")
	DutyScheduleMapper dsMapper;

	@Autowired
	CalendarUtil calendarUtil;
	
	@Autowired
	MailUtil mailUtil;
	
	@Override
	public Map<String, Object> getDutySchedules(int requestMonth, int requestYear) {
		Map<String, Object> resultMap = calendarUtil.getDateInfo(requestMonth, requestYear);
		
		requestMonth = (int) resultMap.get("month") + 1;
		requestYear = (int) resultMap.get("year");
		int spaceCount = (int) resultMap.get("currentMonthStartDay");
		
		List<DutySchedule> dutyScheduleList = dsMapper.select(requestMonth, requestYear);
		if( !dutyScheduleList.isEmpty() ){
			DutySchedule addDummySpace = new DutySchedule(0, 0, 0, 0, 0);
			for( int i = 0; i < spaceCount - 1; i++ ){
				dutyScheduleList.add(i,addDummySpace);
			}
			resultMap.put("dutyScheduleList", dutyScheduleList);
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getInfoForDutyScheduleForm(int requestMonth, int requestYear) {
		List<Employee> emplList = emplService.getEmplList();
		Map<String, Object> resultMap = calendarUtil.getDateInfo(requestMonth, requestYear);
		
		requestMonth = (int) resultMap.get("month") + 1;
		requestYear = (int) resultMap.get("year");
		int spaceCount = (int) resultMap.get("currentMonthStartDay");	

		List<DutySchedule> dutyScheduleList = dsMapper.select(requestMonth, requestYear);
		if( !dutyScheduleList.isEmpty() ){
			DutySchedule addDummySpace = new DutySchedule(0, 0, 0, 0, 0);
			for( int i = 0; i < spaceCount - 1; i++ ){
				dutyScheduleList.add(i,addDummySpace);
			}
			resultMap.put("dutyScheduleList", dutyScheduleList);
		}
		resultMap.put("emplList", emplList);
		return resultMap;
	}	
	
	@Transactional
	@Override
	public void addDutySchedule(MultiDutySchedule dutySchedules) {
		for ( DutySchedule dutySchedule : dutySchedules.getDutySchedules() ) {
			dsMapper.insert(dutySchedule);
		}
	}
	
	@Transactional
	@Override
	public void modifyDutySchedule(MultiDutySchedule dutySchedules) {
		for ( DutySchedule dutySchedule : dutySchedules.getDutySchedules() ) {
			dsMapper.update(dutySchedule);
		}
	}
	
	@Scheduled(cron = "0 57 18 * * *")
	public void sendMail(){	
		
		int date = calendarUtil.getDEFAULT_DATE();
		int month = calendarUtil.getDEFAULT_MONTH() + 1;
		int year = calendarUtil.getDEFAULT_YEAR();
		DutySchedule todayDuty = dsMapper.selectOne(date, month, year);
		DutySchedule tomorrowDuty = dsMapper.selectOne(date+1, month, year);
		String todayDutyEmplEmail = todayDuty.getEmail();
		String todayDutyEmplName = todayDuty.getName();
		String tomorrowDutyEmplEmail = tomorrowDuty.getEmail();
		String tomorrowDutyEmplName = tomorrowDuty.getName();
		logger.debug("todayDutyEmplEmail : " + todayDutyEmplEmail);
		logger.debug("todayDutyEmplName : " + todayDutyEmplName);
		logger.debug("tomorrowDutyEmplEmail : " + tomorrowDutyEmplEmail);
		logger.debug("tomorrowDutyEmplName : " + tomorrowDutyEmplName);
	
		mailUtil.setFrom("nice2seeu86@gmail.com");
		mailUtil.setSubject("디스크리스 당직 근무 알림");
		mailUtil.setTo("nice2seeu86@gmail.com");
		mailUtil.setContent("내일 당직 근무이십니다.");
		mailUtil.mailSender();
		
		mailUtil.setTo("youngsu.park@ahope.co.kr");
		mailUtil.setContent("내일 당직 근무이십니다.");
		mailUtil.mailSender();
		
	//	mailUtil.setTo(todayDutyEmplEmail);
	//	mailUtil.setContent(todayDutyEmplName + "님은 오늘 당직 근무 이십니다.");
	//	mailUtil.mailSender();
	//	mailUtil.setTo(tomorrowDutyEmplEmail);
	//	mailUtil.setContent(tomorrowDutyEmplName + "님은 내일 당직 근무 이십니다.");
	//	mailUtil.mailSender();
	}
}