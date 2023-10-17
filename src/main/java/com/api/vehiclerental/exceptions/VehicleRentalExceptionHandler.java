package com.api.vehiclerental.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VehicleRentalExceptionHandler {

    @ExceptionHandler(value = {VehicleRentalNotFoundException.class})
    public ResponseEntity<Object> handleVehicleRentalNotFoundException(VehicleRentalNotFoundException vehicleRentalNotFoundException) {
        ResponseMessage response = ResponseMessage.builder()
                .message(vehicleRentalNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .success(true)
                .cause(vehicleRentalNotFoundException.getCause())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
