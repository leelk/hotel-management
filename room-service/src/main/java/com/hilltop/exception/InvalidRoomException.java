package com.hilltop.exception;

public class InvalidRoomException extends RoomServiceException {
    public InvalidRoomException(String errorMessage) {
        super(errorMessage);
    }
}
