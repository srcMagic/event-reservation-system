package it.sterz.reservationsystem.mapper;

import it.sterz.reservationsystem.dto.EventDto;
import it.sterz.reservationsystem.dto.response.EventResponseDto;
import it.sterz.reservationsystem.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toDto(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eventReservations", ignore = true)
    Event toEntity(EventDto eventDto);

    List<EventDto> toDtoList(List<Event> events);

    List<Event> toEntityList(List<EventDto> eventDto);

    EventResponseDto toResponseDto(Event event);

    List<EventResponseDto> toResponseDtoList(List<Event> events);
}
