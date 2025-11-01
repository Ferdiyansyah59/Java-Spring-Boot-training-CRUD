package com.coba.spring.coba_crud_spring.exception;


 import lombok.extern.slf4j.Slf4j;
 import org.hibernate.exception.DataException;
 import org.springframework.cglib.core.Local;
 import org.springframework.dao.DataIntegrityViolationException;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.http.converter.HttpMessageNotReadableException;
 import org.springframework.validation.FieldError;
 import org.springframework.web.HttpRequestMethodNotSupportedException;
 import org.springframework.web.bind.MethodArgumentNotValidException;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.RestControllerAdvice;

 import java.time.LocalDate;
 import java.time.LocalDateTime;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.logging.ErrorManager;


class ErrorResponse {
     private int status;
     private String message;
     private LocalDateTime timestamp;

     public ErrorResponse(int status, String message, LocalDateTime timestamp) {
         this.status = status;
         this.message = message;
         this.timestamp =timestamp;
     }

     public int getStatus() {
         return status;
     }

     public void setStatus(int status) {
         this.status = status;
     }

     public String getMessage() {
         return message;
     }

     public void setMessage(String message) {
         this.message = message;
     }

     public LocalDateTime getTimestamp() {
         return timestamp;
     }

     public void setTimestamp(LocalDateTime timestamp) {
         this.timestamp = timestamp;
     }
 }

 @RestControllerAdvice
 @Slf4j
public class GlobalExceptionHandler {

//     INI EXCEPTION CUSTOM, YANG HARUS BIKIN DULU FILE EXCEPTION NYA.....
     @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
         ErrorResponse error = new ErrorResponse(
                 HttpStatus.NOT_FOUND.value(),
                 ex.getMessage(),
                 LocalDateTime.now()
         );
         return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler(ResourceAlreadyExistException.class)
     public ResponseEntity<ErrorResponse> resourceAlreadyExist(ResourceAlreadyExistException ex) {
         ErrorResponse err = new ErrorResponse(
                 HttpStatus.CONFLICT.value(), // 409 conflict
                 ex.getMessage(), // Pesan spesifik dari yang manggil nanti
                 LocalDateTime.now()
         );

         return new ResponseEntity<>(err, HttpStatus.CONFLICT);
     }

//     INI EXCEPTION BAWAAN SPRING BUAT VALIDASI FIELD DAN BUAT HANDLE KALO METHOD GA SUPPORT

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<Map <String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
         Map<String, String> errors = new HashMap<>();
         ex.getBindingResult().getAllErrors().forEach((error) -> {
             String fieldName = ((FieldError) error).getField();
             String errorMessage = error.getDefaultMessage();
             errors.put(fieldName, errorMessage);
         });

         Map<String, Object> response = new HashMap<>();
         response.put("status", HttpStatus.BAD_REQUEST.value());
         response.put("errors", errors);
         response.put("timestamp", LocalDateTime.now());

         return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
     }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
     public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
         ErrorResponse err = new ErrorResponse(
                 HttpStatus.METHOD_NOT_ALLOWED.value(),
                 "Method Not Allowed",
                 LocalDateTime.now()
         );

         return new ResponseEntity<>(err, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // INI BUAT HANDLE 500
    @ExceptionHandler(Exception.class)
     public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
         log.error("An unhandled exception occurred ", ex);

         ErrorResponse err = new ErrorResponse(
                 HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 "An unexpected error occurred. Please try again later",
                 LocalDateTime.now()
         );

         return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Ini buat handle request nya amburadul
    @ExceptionHandler(HttpMessageNotReadableException.class)
     public ResponseEntity<ErrorResponse> handleMalformedJson(HttpMessageNotReadableException ex) {
         ErrorResponse err = new ErrorResponse(
                 HttpStatus.BAD_REQUEST.value(),
                 "Malformed JSON request body. Please Check Yout request systax.",
                 LocalDateTime.now()
         );

         return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
     public ResponseEntity<ErrorResponse> handleDatabaseConflict(DataIntegrityViolationException ex) {
         // Ini ambil pesan error paling spesifik dari databse
         String message = "Database constraint violation. Check your input data.";
         if (ex.getMostSpecificCause() != null) {
             message = ex.getMostSpecificCause().getMessage();
         }
        ErrorResponse err = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                message,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }


}
