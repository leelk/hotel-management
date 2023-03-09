package com.hilltop.service;

import com.hilltop.domain.request.HotelCreateRequest;
import com.hilltop.domain.response.HotelListResponseDto;
import com.hilltop.domain.response.HotelResponseDto;
import com.hilltop.exception.HotelServiceException;
import com.hilltop.exception.InvalidHotelException;
import com.hilltop.model.Hotel;
import com.hilltop.repository.HotelRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {
    //TODO : SL4J logg add

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    public HotelResponseDto saveHotel(HotelCreateRequest hotelCreateRequest) {
        try {
            var hotel = new Hotel(hotelCreateRequest);
            hotelRepository.save(hotel);
            return new HotelResponseDto(hotel);
        } catch (DataAccessException e) {
            throw new HotelServiceException("Saving hotel info into database was failed.", e);
        }
    }

    public Hotel getHotelById(String id) {
        try {
            Optional<Hotel> hotelOptional = hotelRepository.findById(id);
            if (hotelOptional.isPresent()) {
                return hotelOptional.get();
            } else {
                throw new InvalidHotelException("No hotel found for id: " + id);
            }
        } catch (DataAccessException e) {
            throw new HotelServiceException("Reading hotel info from database was failed.", e);
        }
    }

    public HotelListResponseDto getAllHotel() {
        try {
            var hotelList = hotelRepository.findAll();
            List<HotelResponseDto> hotelResponses = hotelList
                    .stream()
                    .map(HotelResponseDto::new)
                    .collect(Collectors.toList());

            return new HotelListResponseDto(hotelResponses);

        } catch (DataAccessException e) {
            throw new HotelServiceException("Reading hotel list from database was failed.", e);
        }


    }

    public Hotel updateHotel(String id, HotelCreateRequest hotelCreateRequest) {
        try {
            var hotelById = getHotelById(id);
            hotelById.update(hotelCreateRequest);
            return hotelRepository.save(hotelById);
        } catch (DataAccessException e) {
            throw new HotelServiceException("Reading hotel list from database was failed.", e);
        }
    }

    public void deleteHotel(String id) {
        try {
            var hotel = getHotelById(id);
            //TODO: Check there are no rooms reserved.
            hotelRepository.delete(hotel);
        } catch (DataAccessException e) {
            throw new HotelServiceException("Deleting a hotel by id from database was failed.", e);
        }
    }
}
