package com.hilltop.domain.response;

import com.hilltop.model.Room;
import lombok.Getter;
import lombok.Setter;

/**
 * RoomResponseDto
 */
@Getter
@Setter
public class RoomResponseDto extends ResponseDto {

    private String id;
    private int roomNumber;
    private String hotelId;

    public RoomResponseDto(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.hotelId = room.getHotelId();
    }
}
