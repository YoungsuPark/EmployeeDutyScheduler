package kr.co.ahope.employee.controller;

import java.util.List;

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

import kr.co.ahope.employee.domain.Employee;
import kr.co.ahope.employee.service.EmplService;

/**
 * Naming Rule :
 * 	empl -> employee
 * 	disp -> display
 * 	info -> information
 */

@Controller
public class EmplController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EmplController.class);

	@Resource(name="emplService")
	private EmplService emplService;
	
	@RequestMapping(value = "/empl", method = RequestMethod.GET)
	public String dispEmplList(Model model){
		List<Employee> list = emplService.getEmplList();
		model.addAttribute("emplList", list);
		return "employee/emplList";
	}
	
	@RequestMapping(value = "/empl/add", method = RequestMethod.POST)
	public String addEmpl(@ModelAttribute("employee") Employee empl, RedirectAttributes redirectAttributes){
		emplService.addEmpl(empl);
		return "redirect:/empl";
	}
	
	@RequestMapping(value = "/empl/modify", method = RequestMethod.POST)
	public String modifyEmplInfo(@ModelAttribute("employee") Employee empl, RedirectAttributes redirectAttributes){
		emplService.modifyEmplInfo(empl);
		return "redirect:/empl";
	}

	@RequestMapping(value = "/empl/delete", method = RequestMethod.POST)
	public String deleteEmpl(@RequestParam(value = "emplId", required = false) int emplId){
		emplService.deleteEmpl(emplId);
		return "redirect:/empl";
	}
}