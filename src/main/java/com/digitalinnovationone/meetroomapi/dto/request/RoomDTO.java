package com.digitalinnovationone.meetroomapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class RoomDTO {

    private Long id;

    @NotEmpty(message = "The name field is necessary.")
    private String name;

    @NotEmpty(message = "The date field is necessary.")
    private String date;

    @NotEmpty(message = "The start hour field is necessary.")
    private String startHour;

    @NotEmpty(message = "The end hour field is necessary.")
    private String endHour;
}
