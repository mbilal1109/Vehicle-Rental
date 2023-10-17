package com.api.vehiclerental.exceptions;

public class VehicleRentalNotFoundException extends RuntimeException{

    public VehicleRentalNotFoundException(String message) {
        super(message);
    }

    public VehicleRentalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
