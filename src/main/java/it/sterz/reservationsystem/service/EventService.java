package it.sterz.reservationsystem.service;

import it.sterz.reservationsystem.dto.EventDto;
import it.sterz.reservationsystem.dto.response.EventResponseDto;
import it.sterz.reservationsystem.entity.Event;
import it.sterz.reservationsystem.exception.NotFoundException;
import it.sterz.reservationsystem.mapper.EventMapper;
import it.sterz.reservationsystem.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public Page<EventResponseDto> getAllEvents(Pageable pageable) {

        Page<Event> eventPage = eventRepository.findAll(pageable);
        return eventPage.map(eventMapper::toResponseDto);
    }

    public EventResponseDto getEventById(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'evento con id " + id + " non trovato"));
        return eventMapper.toResponseDto(event);
    }

    public EventDto createEvent(EventDto eventDto) {

        Event event = eventMapper.toEntity(eventDto);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }

    public EventDto updateEvent(EventDto eventDto, Long id) {

        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento con id " + id + " non trovato"));

        existingEvent.setTitle(eventDto.getTitle());
        existingEvent.setDescription(eventDto.getDescription());
        existingEvent.setDateTime(eventDto.getDateTime());
        existingEvent.setMaxSeats(eventDto.getMaxSeats());

        Event updateEvent = eventRepository.save(existingEvent);
        return eventMapper.toDto(updateEvent);
    }

    public void deleteEvent(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'evento con id " + id + " non trovato"));

        eventRepository.delete(event);
    }
}