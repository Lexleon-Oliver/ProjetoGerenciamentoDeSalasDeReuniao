package com.digitalinnovationone.meetroomapi.resource;

import com.digitalinnovationone.meetroomapi.RoomService.RoomService;
import com.digitalinnovationone.meetroomapi.dto.request.RoomDTO;
import com.digitalinnovationone.meetroomapi.dto.response.MessageResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomResource {
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<RoomDTO> getAllRooms(){
        return roomService.getAll();
    }

    @GetMapping("/rooms/id")
    public RoomDTO getRoomById(@PathVariable Long id){
        return roomService.getById(id);
    }

    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createRoom (@Valid @RequestBody RoomDTO roomDTO){
        return roomService.save(roomDTO);
    }

    @PutMapping("/rooms/id")
    public MessageResponseDTO updateRoom (@PathVariable Long id, @Valid @RequestBody RoomDTO roomDTO){
        return roomService.update(id,roomDTO);
    }

    @DeleteMapping("/rooms/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable Long id){
        roomService.delete(id);

    }

}
