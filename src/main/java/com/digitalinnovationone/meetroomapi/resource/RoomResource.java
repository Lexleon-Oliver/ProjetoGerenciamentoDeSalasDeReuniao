package com.digitalinnovationone.meetroomapi.resource;

import com.digitalinnovationone.meetroomapi.entity.Room;
import com.digitalinnovationone.meetroomapi.exception.ResourceNotFoundException;
import com.digitalinnovationone.meetroomapi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomResource {
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/id")
    public Room getRoomById(@PathVariable Long id){
        return roomRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room not found " + id));
    }

    @PostMapping("/rooms")
    public Room createRoom (@Valid @RequestBody Room room){
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/id")
    public Room updateRoom(@PathVariable Long id, @Valid @RequestBody Room room){
    Room room1 = roomRepository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("Room not found for this id "+ id));
    room1.setName(room.getName());
    room1.setDate(room.getDate());
    room1.setStartHour(room.getStartHour());
    room1.setEndHour(room.getEndHour());
    final Room updateRoom= roomRepository.save(room1);
    return updateRoom;
    }

    @DeleteMapping
    public Map<String, Boolean> deleteRoom(@PathVariable Long id){
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room not found for this id "+ id));
        roomRepository.delete(room);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return response;

    }

}
