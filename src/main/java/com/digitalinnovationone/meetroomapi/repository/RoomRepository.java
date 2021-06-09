package com.digitalinnovationone.meetroomapi.repository;

import com.digitalinnovationone.meetroomapi.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long>{
}
