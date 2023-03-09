package com.hilltop.domain.response;

import com.hilltop.domain.BaseDto;

public abstract class ResponseDto implements BaseDto {
    @Override
    public String toLogJson() {
        return toJson();
    }
}
