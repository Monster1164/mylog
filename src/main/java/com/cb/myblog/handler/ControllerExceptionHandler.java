package com.cb.myblog.handler;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.cb.myblog.myblog.controller"},
        annotations = Controller.class)
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest req , Exception e) throws Exception {


        logger.error("Request URL:{}, Exception: {}",req.getRequestURL(),e.getMessage());

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("url",req.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("/error/error");

        return  modelAndView;
    }




}
