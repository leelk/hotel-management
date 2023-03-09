package com.hilltop.exception;

public class InvalidHotelException extends HotelServiceException{
    public InvalidHotelException(String errorMessage) {
        super(errorMessage);
    }
}
