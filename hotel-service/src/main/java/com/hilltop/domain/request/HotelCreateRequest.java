package com.hilltop.domain.request;

import lombok.Getter;
import lombok.Setter;

/**
 * HotelCreateRequest
 */
@Getter
@Setter
public class HotelCreateRequest extends RequestDto {

    private String name;
    private String description;
    private String city;
    private String address;
    private String telephone;
    private String email;


    @Override
    public String toLogJson() {
        return toJson();
    }

    @Override
    public boolean isRequiredAvailable() {
        return isNonEmpty(name) && isNonEmpty(city) && isNonEmpty(telephone) && isNonEmpty(address);
    }
}
