package kr.co.ahope.dutyschedule.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import kr.co.ahope.common.utils.CalendarUtil;
import kr.co.ahope.common.utils.MailUtil;
import kr.co.ahope.dutyschedule.domain.DutySchedule;
import kr.co.ahope.dutyschedule.domain.MultiDutySchedule;
import kr.co.ahope.dutyschedule.mapper.DutyScheduleMapper;
import kr.co.ahope.employee.controller.EmplController;
import kr.co.ahope.employee.domain.Employee;
import kr.co.ahope.employee.service.EmplService;

@Transactional
@Service(value = "dsService")
public class DutyScheduleServiceImpl implements DutyScheduleService {

	private static final Logger logger = LoggerFactory.getLogger(EmplController.class);

	@Resource(name="emplService")
	EmplService emplService;	
	
	@Resource(name = "dsMapper")
	DutyScheduleMapper dsMapper;
	
	@Resource(name = "transactionManager")
	protected DataSourceTransactionManager txManager;

	@Autowired
	CalendarUtil calendarUtil;
	
	@Autowired
	MailUtil mailUtil;
	
	@Transactional(readOnly=true)
	@Override
	public Map<String, Object> getInfoForDutySchedules(int requestMonth, int requestYear) {
		// for date information
		Map<String, Object> resultMap = calendarUtil.getDateInfo(requestMonth, requestYear);
		requestMonth = (int) resultMap.get("month") + 1;
		requestYear = (int) resultMap.get("year");
		int spaceCount = (int) resultMap.get("currentMonthStartDay") - 1;
		// for duty schedule list
		List<DutySchedule> dutyScheduleList = dsMapper.select(requestMonth, requestYear);
		// If there exists an duty schedule list, it has to be checked empty space on the first week
		if( !dutyScheduleList.isEmpty() ){
			DutySchedule addDummySpace = new DutySchedule(0, 0, 0, 0, 0);
			for( int i = 0; i < spaceCount; i++ ){
				dutyScheduleList.add(i,addDummySpace);
			}
			resultMap.put("dutyScheduleList", dutyScheduleList);
		}
		return resultMap;
	}
	
	@Transactional(readOnly=true)
	@Override
	public Map<String, Object> getInfoForDutyScheduleForm(int requestMonth, int requestYear) {	
		// for date information
		Map<String, Object> resultMap = calendarUtil.getDateInfo(requestMonth, requestYear);	
		requestMonth = (int) resultMap.get("month") + 1;
		requestYear = (int) resultMap.get("year");
		int spaceCount = (int) resultMap.get("currentMonthStartDay") - 1;	
		// If there exists an duty schedule list, it has to be checked empty space on the first week
		List<DutySchedule> dutyScheduleList = dsMapper.select(requestMonth, requestYear);
		if( !dutyScheduleList.isEmpty() ){
			DutySchedule addDummySpace = new DutySchedule(0, 0, 0, 0, 0);
			for( int i = 0; i < spaceCount ; i++ ){
				dutyScheduleList.add(i,addDummySpace);
			}
			resultMap.put("dutyScheduleList", dutyScheduleList);
		}
		// for employee list 
		List<Employee> emplList = emplService.getEmplList();
		resultMap.put("emplList", emplList);
		return resultMap;
	}	
	
	@Override
	public void addDutySchedule(MultiDutySchedule dutySchedules) {
		for ( DutySchedule dutySchedule : dutySchedules.getDutySchedules() ) {
			dsMapper.insert(dutySchedule);
		}
	}

	@Override
	public void modifyDutySchedule(MultiDutySchedule dutySchedules) {
		for ( DutySchedule dutySchedule : dutySchedules.getDutySchedules() ) {
			dsMapper.update(dutySchedule);
		}
	}
	
	@Scheduled(cron = "0 6 13 * * *")
	public void sendMail(){	
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus txStatus= txManager.getTransaction(def);
	    String resultStatus ="무상태";
	    try{
			//for today Duty
			int date = calendarUtil.getCurrentDate();
			int month = calendarUtil.getCurrentMonth();
			int year = calendarUtil.getCurrentYear();
			DutySchedule todayDuty = dsMapper.selectOne(date, month+1, year);
			logger.info("Today is " + year + " / " + (month + 1) + " / " + date);
			// for tomorrow Duty
			Map<String, Object> tomorrowDateInfo = calendarUtil.getTommorowDateInfo(date, month, year);
			int tomorrowDate = (int) tomorrowDateInfo.get("tomorrowDate");
			int tomorrowMonth = (int) tomorrowDateInfo.get("tomorrowMonth");
			int tomorrowYear = (int) tomorrowDateInfo.get("tomorrowYear");
			logger.info("Tomorrow is " + tomorrowYear + " / " + (tomorrowMonth + 1) + " / " + tomorrowDate);
			DutySchedule tomorrowDuty = dsMapper.selectOne(tomorrowDate, tomorrowMonth+1, tomorrowYear);
			// send email
			String todayDutyEmplEmail = todayDuty.getEmail();
			String todayDutyEmplName = todayDuty.getName();
			String tomorrowDutyEmplEmail = tomorrowDuty.getEmail();
			String tomorrowDutyEmplName = tomorrowDuty.getName();
			mailUtil.setFrom("nice2seeu86@gmail.com");
			mailUtil.setSubject("디스크리스 당직 근무 알림");
			mailUtil.setTo(todayDutyEmplEmail);
			mailUtil.setContent(todayDutyEmplName + "님은 오늘 ("+ (month+1) + "/" + date + ") 당직 근무 이십니다.");
			mailUtil.mailSender();
			mailUtil.setTo(tomorrowDutyEmplEmail);
			mailUtil.setContent(tomorrowDutyEmplName + "님은 내일("+ (tomorrowMonth+1) + "/" + tomorrowDate + ") 당직 근무 이십니다.");
			mailUtil.mailSender();		
			txManager.commit(txStatus);
			resultStatus = "성공";
	    } catch (Exception e){
	    	txManager.rollback(txStatus);
	    	resultStatus = "실패";
	    } finally {
	    	logger.debug("status : " + resultStatus);
	    	mailUtil.setFrom("nice2seeu86@gmail.com");
	    	mailUtil.setTo("nice2seeu86@gmail.com");
	    	mailUtil.setSubject("디스크리스 당직 근무 알림 결과 보고");
			mailUtil.setContent( "당직 근무자 메일 전송 상태 : "+ resultStatus );
			mailUtil.mailSender();
	    }
	}
}