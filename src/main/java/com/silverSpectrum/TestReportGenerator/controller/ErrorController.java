package com.silverSpectrum.TestReportGenerator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String handleError(Model model) {
        String errorMessage = "An error occurred. Please try again later.";
        model.addAttribute("errorMessage", errorMessage);
        return "error"; // Return the name of your error page HTML file
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Model model) {
        String errorMessage = "The requested page was not found.";
        model.addAttribute("errorMessage", errorMessage);
        return "error"; // Return the name of your error page HTML file
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public String handleServerError(Model model) {
        String errorMessage = "An internal server error occurred.";
        model.addAttribute("errorMessage", errorMessage);
        return "error"; // Return the name of your error page HTML file
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessForbiddenException.class)
    public String handleForbidden(Model model) {
        String errorMessage = "Access to the requested resource is forbidden.";
        model.addAttribute("errorMessage", errorMessage);
        return "error"; // Return the name of your error page HTML file
    }
}
