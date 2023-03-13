package com.hilltop.exception;

public class InvalidRoomTypeException extends RoomServiceException {

    public InvalidRoomTypeException(String errorMessage) {
        super(errorMessage);
    }
}
