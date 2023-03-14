package com.hilltop.domain.response;

import com.hilltop.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * HotelResponseDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseDto extends ResponseDto {
    private String id;
    private String name;
    private String description;
    private String city;
    private String telephone;
    private String email;

    public HotelResponseDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.city = hotel.getCity();
        this.telephone = hotel.getTelephone();
        this.email = hotel.getEmail();
    }
}
