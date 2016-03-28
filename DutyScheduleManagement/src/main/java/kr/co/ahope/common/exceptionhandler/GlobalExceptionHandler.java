package kr.co.ahope.common.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.co.ahope.employee.controller.EmplController;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(EmplController.class);
	
	@ExceptionHandler(Exception.class)
	public void handleException(Exception e) {
		logger.debug("Exception : " + e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public String haddleRuntimeException(Model model, RuntimeException e) {
		model.addAttribute("exceptionMessage", e.getMessage());
		logger.debug("exceptionMessage : " + e.getMessage());
		return "exceptionHandle";
	}
}
