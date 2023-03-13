package com.hilltop.domain.response;

import com.hilltop.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
