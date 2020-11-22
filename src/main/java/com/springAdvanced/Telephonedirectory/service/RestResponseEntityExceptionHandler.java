package com.springAdvanced.Telephonedirectory.service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public RestResponseEntityExceptionHandler() {
        super();
    }


/*        @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }*/

    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, RuntimeException e) throws RuntimeException {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    // API

    // 400

   /* @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        // ex.getCause() instanceof JsonMappingException, JsonParseException // for additional information later on
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }


    // 404

    @ExceptionHandler(value = { EntityNotFoundException.class, MyResourceNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // 409

    @ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
    protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 412

    // 500

    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    *//*500*//*public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
*/
}