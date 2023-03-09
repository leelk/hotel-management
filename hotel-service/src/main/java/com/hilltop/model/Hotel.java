package com.hilltop.model;

import com.hilltop.domain.request.HotelCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

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
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String city;
    // collect the full address, tp, email, image ulrs.


    public Hotel(HotelCreateRequest hotelCreateRequest) {
        this.id = HOTEL_ID_PREFIX + UUID.randomUUID();
        this.name = hotelCreateRequest.getName();
        this.description = hotelCreateRequest.getDescription();
        this.city = hotelCreateRequest.getCity();
    }

    public void update(HotelCreateRequest hotelCreateRequest) {
        this.name = hotelCreateRequest.getName();
        this.description = hotelCreateRequest.getDescription();
        this.city = hotelCreateRequest.getCity();
    }
}
