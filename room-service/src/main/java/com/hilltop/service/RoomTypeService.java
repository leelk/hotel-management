package com.hilltop.service;

import com.hilltop.domain.request.RoomTypeCreateRequestDto;
import com.hilltop.exception.InvalidRoomTypeException;
import com.hilltop.exception.RoomServiceException;
import com.hilltop.model.RoomType;
import com.hilltop.repository.RoomTypeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }


    public RoomType saveRoomType(RoomTypeCreateRequestDto roomTypeCreateRequestDto) {
        try {
            var roomType = new RoomType(roomTypeCreateRequestDto);
            return roomTypeRepository.save(roomType);
        } catch (DataAccessException e) {
            throw new RoomServiceException("Saving room type info into database was failed.", e);
        }
    }


    public RoomType getRoomType(String id) {
        try {
            Optional<RoomType> roomTypeOptional = roomTypeRepository.findById(id);
            if (roomTypeOptional.isPresent()) {
                return roomTypeOptional.get();
            } else {
                throw new InvalidRoomTypeException("Invalid room type id: " + id);
            }
        } catch (DataAccessException e) {
            throw new RoomServiceException("Getting room type info into database was failed.", e);
        }
    }
}
