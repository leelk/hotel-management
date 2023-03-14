package com.hilltop.domain.response;


import com.hilltop.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * HotelCreateResponseDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelCreateResponseDto extends ResponseDto {

    private String id;
    private String name;
    private String description;
    private String city;
    private String address;
    private String telephone;
    private String email;

    public HotelCreateResponseDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.description = hotel.getDescription();
        this.city = hotel.getCity();
        this.address = hotel.getAddress();
        this.telephone = hotel.getTelephone();
        this.email = hotel.getEmail();
    }
}
