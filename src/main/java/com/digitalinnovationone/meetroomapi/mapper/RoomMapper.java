package com.digitalinnovationone.meetroomapi.mapper;

import com.digitalinnovationone.meetroomapi.dto.request.RoomDTO;
import com.digitalinnovationone.meetroomapi.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.Target;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper((RoomMapper.class));

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    Room toModel(RoomDTO roomDTO);
    RoomDTO toDTO(Room room);
}
