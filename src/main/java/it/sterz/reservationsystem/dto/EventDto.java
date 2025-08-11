package it.sterz.reservationsystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    @NotBlank(message = "Il titolo è obbligatorio")
    @Size(max = 100, message = "Il titolo non può superare i 100 caratteri")
    private String title;

    @Size(max = 500, message = "La descrizione non può superare i 500 caratteri")
    private String description;

    @NotNull(message = "La data e l'ora dell'evento è obbligatoria")
    @Future(message = "La data deve essere nel futuro")
    private LocalDateTime dateTime;

    @NotNull(message = "Il numero massimo di posti è richiesto")
    @Min(value = 1, message = "Deve esserci almeno un posto disponibile")
    @Max(value = 100, message = "Il numero massimo non può superare i 100")
    private Integer maxSeats;
}