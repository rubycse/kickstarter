package com.rubikslab.kickstarter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author lutfun
 * @since 11/1/18
 */
@ControllerAdvice
@Slf4j
public class AppControllerAdvice {

    @ExceptionHandler(Exception.class)
    public String exception(Exception ex, WebRequest request) {
        request.setAttribute("errorMsg", ex.getMessage(), WebRequest.SCOPE_REQUEST);
        printToLog(ex.getMessage(), ex);
        return "error";
    }

    @ExceptionHandler(RecordNotFound.class)
    public String recordNotFound(Exception ex, WebRequest request) {
        request.setAttribute("errorMsgKey", "error.recordNotFound", WebRequest.SCOPE_REQUEST);
        printToLog(ex.getMessage(), ex);
        return "error";
    }

    private void printToLog(String message, Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        log.error(message + "\n" + sw.toString());
    }
}
