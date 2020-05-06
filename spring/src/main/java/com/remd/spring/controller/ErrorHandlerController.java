package com.remd.spring.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.remd.spring.enums.errors.ErrorImagePath;
import com.remd.spring.enums.errors.ErrorMessages;

@Controller
public class ErrorHandlerController implements ErrorController {
	
	@RequestMapping("/error")
	public String handleServerError(HttpServletRequest request,Model model) {
		String errorPageView = "error";
		String errorImage = "/images/error/";
		String errorMessage = "Oops! Something went wrong.";
		Object servletStatus = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (servletStatus != null) {
			//Get Integer status code
			Integer errorCode = Integer.valueOf(servletStatus.toString());
			
			if(errorCode == HttpStatus.NOT_FOUND.value()) {
				errorMessage = ErrorMessages.ERROR404.getErrorMessage();
				errorImage += ErrorImagePath.ERROR404.getErrorImagePath();
				
			} else if (errorCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				errorMessage = ErrorMessages.ERROR500.getErrorMessage();
				errorImage += ErrorImagePath.ERROR500.getErrorImagePath();
			}
			model.addAttribute("errorMessage",errorMessage);
			model.addAttribute("errorImage",errorImage);
			model.addAttribute("errorCode", errorCode);
		}
		return errorPageView;
	}
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
