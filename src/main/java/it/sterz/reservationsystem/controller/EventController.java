package it.sterz.reservationsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.sterz.reservationsystem.dto.EventDto;
import it.sterz.reservationsystem.dto.response.EventResponseDto;
import it.sterz.reservationsystem.service.EventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@Tag(name = "Event Controller", description = "Gestisce operazioni CRUD per un evento")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/page")
    @Operation(summary = "Restituisce tutti gli eventi con paginazione e ordinamento")
    public ResponseEntity<Page<EventResponseDto>> getAllEvents(@RequestParam(defaultValue = "0") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<EventResponseDto> eventResponse = eventService.getAllEvents(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(eventResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Restituisce un Evento per l'id specificato")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventById(id));
    }

    @PostMapping
    @Operation(summary = "Creazione di un Evento")
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiornamento di un Evento ")
    ResponseEntity<EventDto> updateEvent(@PathVariable Long id,
                                         @Valid @RequestBody EventDto eventDto) {

        return ResponseEntity.status(HttpStatus.OK).body(eventService.updateEvent(eventDto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un Evento per l'id specificato")
    ResponseEntity<Void> deleteEvent(@PathVariable Long id) {

        eventService.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}