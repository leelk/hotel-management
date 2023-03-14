package com.hilltop.repository;

import com.hilltop.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoomRepository
 */
public interface RoomRepository extends JpaRepository<Room, String> {
}
