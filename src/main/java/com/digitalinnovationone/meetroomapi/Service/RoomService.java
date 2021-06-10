package com.digitalinnovationone.meetroomapi.Service;

import com.digitalinnovationone.meetroomapi.dto.request.RoomDTO;
import com.digitalinnovationone.meetroomapi.dto.response.MessageResponseDTO;

import java.util.List;

public interface RoomService {

    List<RoomDTO> getAll();
    RoomDTO getById(Long id) ;
    MessageResponseDTO save(RoomDTO roomDTO);
    MessageResponseDTO update(Long id, RoomDTO roomDTO);

    MessageResponseDTO delete(Long id);
//    MessageResponseDTO delete(Long id);
}
