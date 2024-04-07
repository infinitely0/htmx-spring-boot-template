package com.example.site;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

  /*
   * A global exception handler. In some cases this makes sense but usually these methods should be
   * specific to the controller they're handling exceptions for. They are all here for simplicity.
   */

  @ExceptionHandler(Exception.class)
  public ModelAndView handleRuntimeException(RuntimeException e, HttpServletResponse response) {
    log.error("Internal Server Error", e);
    response.addHeader("HX-Redirect", "/internal-server-error");
    ModelAndView mv = new ModelAndView("errors/500");
    mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    return mv;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ModelAndView handleConstraintViolationException(ConstraintViolationException e) {
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    ConstraintViolation<?> violation = violations.iterator().next();
    ModelAndView mv = new ModelAndView("errors/alert");
    mv.addObject("error", violation.getMessage());
    mv.setStatus(HttpStatus.BAD_REQUEST);
    return mv;
  }

  @ExceptionHandler(TransactionSystemException.class)
  public ModelAndView handleTransactionSystemException(
      TransactionSystemException e, HttpServletResponse response) {
    Throwable cause = e.getRootCause();
    if (cause instanceof ConstraintViolationException) {
      return handleConstraintViolationException((ConstraintViolationException) cause);
    }
    // On updating a resource, the cause may not be a ConstraintViolationException
    return handleRuntimeException(e, response);
  }
}
