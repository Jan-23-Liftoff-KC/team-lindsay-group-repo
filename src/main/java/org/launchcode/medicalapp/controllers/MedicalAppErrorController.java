package org.launchcode.medicalapp.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

// thymeleaf automatically picks up error file if it is in resources/templates
// I had to rename the error.html file that was there before to do this. it is now called notAuthorized.html
// created a class called MedicalAppErrorController that implements Spring's ErrorController
// also had to disable the whitelabel in application.properties

@Controller
public class MedicalAppErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        return "error";
    }
}
