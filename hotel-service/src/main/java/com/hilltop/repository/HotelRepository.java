package com.hilltop.repository;

import com.hilltop.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * HotelRepository
 */
public interface HotelRepository extends JpaRepository<Hotel, String> {
}
