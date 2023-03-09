package com.hilltop.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelCreateRequest extends RequestDto {

    private String name;
    private String description;
    private String city;


    @Override
    public String toLogJson() {
        return toJson();
    }
}
