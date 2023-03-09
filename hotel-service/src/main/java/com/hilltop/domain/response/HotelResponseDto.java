package com.hilltop.domain.response;

import com.hilltop.model.Hotel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelResponseDto extends ResponseDto {
    private String id;
    private String name;
    private String description;
    private String city;

    public HotelResponseDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.city = hotel.getCity();
    }
}
