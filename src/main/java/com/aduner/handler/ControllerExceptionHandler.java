package com.aduner.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截器
 * 如果不是已经定义的错误都返回error页面
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler
    public ModelAndView exceptionHandler(HttpServletRequest req,Exception e) throws Exception {
        logger.error("Request Url: {}  Exception: {} ",req.getRequestURI(),e);
        // 如果错误不是已经定义的错误
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        ModelAndView mav=new ModelAndView();
        mav.addObject("url",req.getRequestURI());
        mav.addObject("exception",e);
        mav.setViewName("error/error");
        return mav;
    }
}
