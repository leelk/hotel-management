package com.hilltop.domain.response;

import com.hilltop.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreateResponseDto extends ResponseDto {
    private String id;
    private int roomNumber;
    private String hotelId;

    public RoomCreateResponseDto(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.hotelId = room.getHotelId();
    }
}
