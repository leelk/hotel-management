package com.hilltop.contoller;

import com.hilltop.configuration.Translator;
import com.hilltop.domain.request.RoomCreateRequestDto;
import com.hilltop.domain.response.RoomResponseDto;
import com.hilltop.enums.ErrorResponseStatusType;
import com.hilltop.enums.SuccessResponseStatusType;
import com.hilltop.exception.InvalidRoomException;
import com.hilltop.exception.RoomServiceException;
import com.hilltop.service.RoomService;
import com.hilltop.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * RoomController
 */
@RestController
@RequestMapping("/api/v1/room")
@Slf4j
public class RoomController extends Controller {

    private final RoomService roomService;

    public RoomController(Translator translator, RoomService roomService) {
        super(translator);
        this.roomService = roomService;
    }

    /**
     * This endpoint used to save a room.
     *
     * @param roomCreateRequestDto roomCreateRequestDto
     * @return roomCreateResponseDto
     */
    @PostMapping
    public ResponseEntity<ResponseWrapper> saveRoom(@RequestBody RoomCreateRequestDto roomCreateRequestDto) {
        try {
            //TODO check validations
            var roomCreateResponseDto = roomService.saveRoom(roomCreateRequestDto);
            return getSuccessResponse(roomCreateResponseDto, SuccessResponseStatusType.CREATE_ROOM);
        } catch (RoomServiceException e) {
            log.error("Saving room was failed for hotel id: {}", roomCreateRequestDto.getHotelId(), e);
            return getInternalServerError();
        }
    }

    /**
     * This endpoint used to get room by id.
     *
     * @param id hotelId
     * @return roomResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> getRoom(@PathVariable String id) {
        try {
            var room = roomService.getRoom(id);
            var roomResponseDto = new RoomResponseDto(room);
            return getSuccessResponse(roomResponseDto, SuccessResponseStatusType.GET_ROOM);
        } catch (InvalidRoomException e) {
            log.error("Invalid room id to get room details.");
            return getErrorResponse(ErrorResponseStatusType.INVALID_ROOM_ID);
        } catch (RoomServiceException e) {
            log.error("Getting room by id was failed.", e);
            return getInternalServerError();
        }
    }
}
