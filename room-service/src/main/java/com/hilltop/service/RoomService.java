package com.hilltop.service;

import com.hilltop.domain.request.RoomCreateRequestDto;
import com.hilltop.domain.response.RoomCreateResponseDto;
import com.hilltop.exception.InvalidRoomException;
import com.hilltop.exception.RoomServiceException;
import com.hilltop.model.Room;
import com.hilltop.model.RoomType;
import com.hilltop.repository.RoomRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeService roomTypeService;

    public RoomService(RoomRepository roomRepository, RoomTypeService roomTypeService) {
        this.roomRepository = roomRepository;
        this.roomTypeService = roomTypeService;
    }

    public RoomCreateResponseDto saveRoom(RoomCreateRequestDto roomCreateRequestDto) {


        var roomType = roomTypeService.getRoomType(roomCreateRequestDto.getRoomTypeId());

        var room = new Room(roomCreateRequestDto,roomType);
        try {
            roomRepository.save(room);
            return new RoomCreateResponseDto(room);
        } catch (DataAccessException e) {
            throw new RoomServiceException("Saving room info into database was failed.", e);
        }
    }

    public Room getRoom(String roomId) {
        try {
            Optional<Room> roomOptional = roomRepository.findById(roomId);
            if (roomOptional.isPresent()) {
                return roomOptional.get();
            } else {
                //TODO: Change the error
                throw new InvalidRoomException("No room found for id: " + roomId);
            }
        } catch (DataAccessException e) {
            throw new RoomServiceException("Getting room info into database was failed.", e);
        }
    }

}
