package com.postexpress.Postrexpress.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle 404 - Resource Not Found
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleResourceNotFoundException(NoHandlerFoundException ex) {
        logger.error("Resource Not Found", ex);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/404");
        return modelAndView;
    }

}