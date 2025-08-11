package it.sterz.reservationsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private Integer maxSeats;
}