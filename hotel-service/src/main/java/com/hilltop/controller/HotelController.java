package com.hilltop.controller;

import com.hilltop.configuration.Translator;
import com.hilltop.domain.request.HotelCreateRequestDto;
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

/**
 * HotelController
 */
@RestController
@RequestMapping("/api/v1/hotel")
@Slf4j
public class HotelController extends Controller {
    private final HotelService hotelService;

    public HotelController(Translator translator, HotelService hotelService) {
        super(translator);
        this.hotelService = hotelService;
    }

    /**
     * This endpoint used to save a hotel.
     *
     * @param hotelCreateRequest hotelCreateRequest
     * @return hotelSaveResponseDto
     */
    @PostMapping
    public ResponseEntity<ResponseWrapper> saveHotel(@RequestBody HotelCreateRequestDto hotelCreateRequest) {
        try {
            if (!hotelCreateRequest.isRequiredAvailable()) {
                log.error("Missing required filed to save a hotel.");
                return getErrorResponse(ErrorResponseStatusType.MISSING_REQUIRED_FIELDS);
            }
            var hotelSaveResponseDto = hotelService.saveHotel(hotelCreateRequest);
            return getSuccessResponse(hotelSaveResponseDto, SuccessResponseStatusType.CREATE_HOTEL);
        } catch (HotelServiceException e) {
            log.error("Saving hotel was failed.", e);
            return getInternalServerError();
        }
    }

    /**
     * This endpoint used to get hotel by id.
     *
     * @param id hotel id
     * @return hotelResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper> getHotel(@PathVariable String id) {
        try {
            var hotel = hotelService.getHotelById(id);
            var hotelResponseDto = new HotelResponseDto(hotel);
            return getSuccessResponse(hotelResponseDto, SuccessResponseStatusType.READ_HOTEL);
        } catch (InvalidHotelException e) {
            log.error("Invalid hotel id to get hotel details.");
            return getErrorResponse(ErrorResponseStatusType.INVALID_HOTEL_ID);
        } catch (HotelServiceException e) {
            log.error("Returning hotel list was failed.", e);
            return getInternalServerError();
        }
    }

    /**
     * This endpoint used to get hotel list.
     *
     * @return hotelListResponseDto
     */
    @GetMapping
    //TODO: paginate the hotel response.
    public ResponseEntity<ResponseWrapper> getHotelList() {
        try {
            var hotelListResponseDto = hotelService.getAllHotel();
            return getSuccessResponse(hotelListResponseDto, SuccessResponseStatusType.READ_HOTEL_LIST);
        } catch (HotelServiceException e) {
            log.error("Returning hotel list was failed.", e);
            return getInternalServerError();
        }
    }

    /**
     * This endpoint used to update a hotel by id.
     *
     * @param id                 hotel id
     * @param hotelCreateRequest hotelCreateRequest
     * @return hotelResponseDto
     */
    @PostMapping("/{id}")
    public ResponseEntity<ResponseWrapper> updateHotel(@PathVariable String id,
                                                       @RequestBody HotelCreateRequestDto hotelCreateRequest) {
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

    /**
     * This endpoint used to delete a hotel by id.
     *
     * @param id hotel id
     * @return SuccessResponseStatus
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper> deleteHotel(@PathVariable String id) {
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
