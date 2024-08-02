package com.github.sysfutexholidaycamp.holidaycampbackend.api.error.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403"; // TODO: create error/403 page
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404"; // TODO: create error/404 page
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500"; // TODO: create error/500 page
            } else {
                model.addAttribute("statusCode", statusCode);
            }
        }

        return "error/error"; // TODO: create error/error page
    }
}
