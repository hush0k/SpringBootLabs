package kz.kbtu.tsis4.mappers;

import kz.kbtu.tsis4.domain.Event;
import kz.kbtu.tsis4.dto.request.EventCreateDto;
import kz.kbtu.tsis4.dto.request.EventUpdateDto;
import kz.kbtu.tsis4.dto.response.EventResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper( componentModel = "spring")
public interface EventMapper {
    //EventCreateDto -> Event
    Event toEvent(EventCreateDto dto);

    //Event -> EventResponseDto
    EventResponseDto toResponseDto(Event event);

    //List<Event> -> List<EventResponseDto>
    List<EventResponseDto> toResponseDtoList(List<Event> events);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEventDto(EventUpdateDto dto, @MappingTarget Event event);
}
