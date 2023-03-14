package com.hilltop.domain.response;

import com.hilltop.model.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * RoomTypeCreateResponse
 */
@Getter
@Setter
public class RoomTypeCreateResponse extends ResponseDto {
    private String id;
    private String roomType;
    private BigDecimal pricePerNight;

    public RoomTypeCreateResponse(RoomType roomType) {
        this.id = roomType.getId();
        this.roomType = roomType.getName();
        this.pricePerNight = roomType.getPricePerNight();
    }
}
