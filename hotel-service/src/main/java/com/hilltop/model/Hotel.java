package com.hilltop.model;

import com.hilltop.domain.request.HotelCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Hotel Entity
 */
@Entity
@Table(name = "hotel")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Transient
    private static final String HOTEL_ID_PREFIX = "hid-";

    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String telephone;
    private String email;

    public Hotel(HotelCreateRequestDto hotelCreateRequest) {
        this.id = HOTEL_ID_PREFIX + UUID.randomUUID();
        this.name = hotelCreateRequest.getName();
        this.description = hotelCreateRequest.getDescription();
        this.city = hotelCreateRequest.getCity();
        this.address = hotelCreateRequest.getAddress();
        this.telephone = hotelCreateRequest.getTelephone();
        this.email = hotelCreateRequest.getEmail();
    }

    //TODO complete the update method
    public void update(HotelCreateRequestDto hotelCreateRequest) {
        this.name = hotelCreateRequest.getName();
        this.description = hotelCreateRequest.getDescription();
        this.city = hotelCreateRequest.getCity();
    }
}
