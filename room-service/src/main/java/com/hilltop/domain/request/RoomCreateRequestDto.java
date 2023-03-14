package com.hilltop.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * RoomCreateRequestDto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomCreateRequestDto extends RequestDto{

    private int roomNumber;
    private String hotelId;
    private int paxCount;
    private String roomTypeId;
    private List<String> imageUrls;

    @Override
    public String toLogJson() {
        return toJson();
    }
}
