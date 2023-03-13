package com.hilltop.model;

import com.hilltop.domain.request.RoomCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "room")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Transient
    private static final String ROOM_ID_PREFIX = "rid-";

    @Id
    private String id;
    private int roomNumber;
    private String hotelId;
    private int paxCount;
    @ManyToOne
    private RoomType roomType;
    @ElementCollection
    private List<String> imageUrls;


    public Room(RoomCreateRequestDto roomCreateRequestDto, RoomType roomType) {
        this.id = ROOM_ID_PREFIX + UUID.randomUUID();
        this.roomNumber = roomCreateRequestDto.getRoomNumber();
        this.paxCount = roomCreateRequestDto.getPaxCount();
        this.hotelId = roomCreateRequestDto.getHotelId();
        this.roomType = roomType;
        this.imageUrls = roomCreateRequestDto.getImageUrls();
    }
}
