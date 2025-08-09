package it.sterz.reservationsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.sterz.reservationsystem.dto.UserDto;
import it.sterz.reservationsystem.dto.response.UserResponseDto;
import it.sterz.reservationsystem.mapper.UserMapper;
import it.sterz.reservationsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "Gestisce operazioni CRUD per un utente")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
    }

    @GetMapping("/page")
    @Operation(summary = "Restituisce tutti gli utenti con paginazione")
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer size,
                                                             @RequestParam(defaultValue = "id") String sortBy,
                                                             @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserResponseDto> userResponse = userService.getAllUsers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Restituisce un Utente per l'id specificato")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @PostMapping
    @Operation(summary = "Creazione di un Utente")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiornamento di un Utente")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UserDto userDto) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un utente per ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}