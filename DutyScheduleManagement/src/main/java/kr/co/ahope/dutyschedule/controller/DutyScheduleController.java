package kr.co.ahope.dutyschedule.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.ahope.dutyschedule.domain.MultiDutySchedule;
import kr.co.ahope.dutyschedule.service.DutyScheduleService;
import kr.co.ahope.employee.controller.EmplController;

/**
 * naming rule
 * 
 * DutySchedule -> ds
 * Information -> info
 * display -> disp
 * 
 */
@RequestMapping("/schedule")
@Controller
public class DutyScheduleController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmplController.class);
	
	@Resource(name="dsService")
	private DutyScheduleService dsService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String dispDutySchedule( @RequestParam(value="month", defaultValue ="-99", required=false) int month, 
									@RequestParam(value="year", defaultValue="-99", required=false) int year, Model model){
		Map<String, Object> resultMap = dsService.getInfoForDutySchedules(month, year);
		//for calendar
		model.addAttribute("date", resultMap.get("date"));
		model.addAttribute("month", resultMap.get("month"));
		model.addAttribute("year", resultMap.get("year"));
		model.addAttribute("weekCount", resultMap.get("weekCount"));
		model.addAttribute("currentCalendarDay",resultMap.get("currentCalendarDay"));
		//for duty schedule list
		model.addAttribute("dutyScheduleList", resultMap.get("dutyScheduleList"));
		return "dutyschedule/scheduleList";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String dispDutySchedulingForm( @RequestParam(value="month", defaultValue ="-99", required=false) int month, 
										  @RequestParam(value="year", defaultValue="-99", required=false) int year, Model model) {
		Map<String, Object> resultMap = dsService.getInfoForDutyScheduleForm(month, year);
		//for calendar
		model.addAttribute("month", resultMap.get("month"));
		model.addAttribute("year", resultMap.get("year"));
		model.addAttribute("weekCount", resultMap.get("weekCount"));
		model.addAttribute("currentCalendarDay",resultMap.get("currentCalendarDay"));
		//for duty schedule list 
		model.addAttribute("dutyScheduleList", resultMap.get("dutyScheduleList"));
		//for employee list, to edit duty schedules
		model.addAttribute("emplList", resultMap.get("emplList"));
		return "dutyschedule/scheduleUpForm";
	}
	
	@RequestMapping(value = "/write_ok", method = RequestMethod.POST)
	public String addDutySchedule(@ModelAttribute("dutySchedules") MultiDutySchedule dutySchedules, RedirectAttributes redirectAttributes) {
		int determineStatus = 0; //check all emplDutyScheduleId to decide whether new data or not
		for ( int i = 0; i< dutySchedules.getDutySchedules().size(); i++){
			determineStatus += dutySchedules.getDutySchedules().get(i).getEmplDutyScheduleId();
		}
		if( determineStatus == 0){
			logger.info("New");
			dsService.addDutySchedule(dutySchedules);
		} else {
			logger.info("Modify");
			dsService.modifyDutySchedule(dutySchedules);
		}
		return "redirect:/schedule";
	}
}