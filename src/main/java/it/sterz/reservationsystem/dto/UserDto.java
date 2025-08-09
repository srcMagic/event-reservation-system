package it.sterz.reservationsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Il campo nome non può essere vuoto")
    @Size(min = 2, max = 50, message = "Il campo deve avere tra i 2 e i 50 caratteri")
    private String name;

    @NotBlank(message = "Il campo email non può essere vuoto")
    @Email(message = "Email non valida")
    private String email;
}
