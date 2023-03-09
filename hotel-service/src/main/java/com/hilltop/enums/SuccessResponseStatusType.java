package com.hilltop.enums;

import lombok.Getter;

@Getter
public enum SuccessResponseStatusType {

    CREATE_HOTEL(2000, "Successfully created the hotel."),
    READ_HOTEL(2001, "Successfully returned the hotel by id."),
    READ_HOTEL_LIST(2002, "Successfully returned the hotel list."),
    UPDATE_HOTEL(2003, "Successfully update the hotel."),
    DELETE_HOTEL(2004,"Successfully delete the hotel.");
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
