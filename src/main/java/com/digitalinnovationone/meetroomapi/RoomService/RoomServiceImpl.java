package com.digitalinnovationone.meetroomapi.RoomService;

import com.digitalinnovationone.meetroomapi.dto.request.RoomDTO;
import com.digitalinnovationone.meetroomapi.dto.response.MessageResponseDTO;
import com.digitalinnovationone.meetroomapi.entity.Room;
import com.digitalinnovationone.meetroomapi.exception.ResourceNotFoundException;
import com.digitalinnovationone.meetroomapi.mapper.RoomMapper;
import com.digitalinnovationone.meetroomapi.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private static final RoomMapper roomMapper= RoomMapper.INSTANCE;

    @Override
    public List<RoomDTO> getAll() {
        List<Room> allRooms =roomRepository.findAll();
        return allRooms.stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO getById(Long id) {
        Room room = verifyIfExists(id);
        return roomMapper.toDTO(room);
    }

    @Override
    public MessageResponseDTO save(RoomDTO roomDTO) {
        Room roomToSave = roomMapper.toModel(roomDTO);
        Room savedRoom = roomRepository.save(roomToSave);
        return createMessageResponse(savedRoom.getId(),"Created room with id ");
    }

    @Override
    public MessageResponseDTO update(Long id, RoomDTO roomDTO) {
       verifyIfExists(id);
       Room roomToUpdate = roomMapper.toModel(roomDTO);
       Room updatedRoom = roomRepository.save(roomToUpdate);
       return createMessageResponse(updatedRoom.getId(), "Updated room with id ");
    }

    @Override
    public void delete(Long id) {
        verifyIfExists(id);
        roomRepository.deleteById(id);

    }
    private Room verifyIfExists(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with id "+id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
