package com.hilltop.controller;

import com.hilltop.configuration.Translator;
import com.hilltop.domain.request.HotelCreateRequest;
import com.hilltop.domain.response.HotelResponseDto;
import com.hilltop.enums.ErrorResponseStatusType;
import com.hilltop.enums.SuccessResponseStatusType;
import com.hilltop.exception.HotelServiceException;
import com.hilltop.exception.InvalidHotelException;
import com.hilltop.service.HotelService;
import com.hilltop.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
@Slf4j
public class HotelController extends Controller {
    private final HotelService hotelService;


    public HotelController(Translator translator, HotelService hotelService) {
        super(translator);
        this.hotelService = hotelService;
    }


    @PostMapping
    public ResponseEntity<ResponseWrapper> saveHotel(@RequestBody HotelCreateRequest hotelCreateRequest) {
        try {
            var hotelSaveResponseDto = hotelService.saveHotel(hotelCreateRequest);
            return getSuccessResponse(hotelSaveResponseDto, SuccessResponseStatusType.CREATE_HOTEL);
        } catch (HotelServiceException e) {

            //TODO : Check here
            log.error("Returning hotel list was failed.", e);
            return getInternalServerError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> getHotel(@PathVariable String id) {
        try {
            var hotel = hotelService.getHotelById(id);
            var hotelSaveResponseDto = new HotelResponseDto(hotel);
            return getSuccessResponse(hotelSaveResponseDto, SuccessResponseStatusType.READ_HOTEL);
        } catch (InvalidHotelException e) {
            log.error("Invalid hotel id to get hotel details.");
            return getErrorResponse(ErrorResponseStatusType.INVALID_HOTEL_ID);
        } catch (HotelServiceException e) {
            log.error("Returning hotel list was failed.", e);
            return getInternalServerError();
        }
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getHotelList() {
        try {
            var hotelListResponseDtos = hotelService.getAllHotel();
            return getSuccessResponse(hotelListResponseDtos, SuccessResponseStatusType.READ_HOTEL_LIST);
        } catch (HotelServiceException e) {
            log.error("Returning hotel list was failed.", e);
            return getInternalServerError();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseWrapper> updateHotel(@PathVariable String id,
                                                       @RequestBody HotelCreateRequest hotelCreateRequest) {
        try {

            var hotel = hotelService.updateHotel(id, hotelCreateRequest);
            var hotelResponseDto = new HotelResponseDto(hotel);
            return getSuccessResponse(hotelResponseDto, SuccessResponseStatusType.UPDATE_HOTEL);
        } catch (InvalidHotelException e) {
            log.error("Invalid hotel id to update hotel details.");
            return getErrorResponse(ErrorResponseStatusType.INVALID_HOTEL_ID);
        } catch (HotelServiceException e) {
            log.error("Updating hotel by id was failed.", e);
            return getInternalServerError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper> updateHotel(@PathVariable String id) {
        try {
            hotelService.deleteHotel(id);
            return getSuccessResponse(null, SuccessResponseStatusType.DELETE_HOTEL);
        } catch (InvalidHotelException e) {
            log.error("Invalid hotel id to update hotel details.");
            return getErrorResponse(ErrorResponseStatusType.INVALID_HOTEL_ID);
        } catch (HotelServiceException e) {
            log.error("Updating hotel by id was failed.", e);
            return getInternalServerError();
        }
    }

}
