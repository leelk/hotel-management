package com.hilltop.enums;

import lombok.Getter;

@Getter
public enum SuccessResponseStatusType {

    CREATE_ROOM(2000, "Successfully created the room."),
    GET_ROOM(2001,"Successfully returned the room."),
    CREATE_ROOM_TYPE(2002,"Successfully created the room type.");

    private final int code;
    private final String message;

    SuccessResponseStatusType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Success code covert into string to read display message from success property file
     *
     * @param successCode successCode
     * @return string code
     */
    public String getCodeString(int successCode) {
        return Integer.toString(successCode);
    }
}
