package kz.kbtu.tsis4.service;

import kz.kbtu.tsis4.domain.Event;
import kz.kbtu.tsis4.dto.request.EventCreateDto;
import kz.kbtu.tsis4.dto.request.EventUpdateDto;
import kz.kbtu.tsis4.dto.response.EventResponseDto;
import kz.kbtu.tsis4.exceptions.NotFoundException;
import kz.kbtu.tsis4.mappers.EventMapper;
import kz.kbtu.tsis4.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    private Event getExisting(UUID id){
        return eventRepository.findById(id)
                .orElseThrow(
                        () -> {
                            log.warn("Event not found with id {}", id);
                            return new NotFoundException("Event with id: " + id + " not found");
                        });
    }

    public EventResponseDto create(EventCreateDto dto){
        Event event = eventMapper.toEvent(dto);
        Event saved =  eventRepository.save(event);

        log.info("Event created with name {}", saved.getName());
        return eventMapper.toResponseDto(saved);
    }


    public EventResponseDto updateEvent(UUID id, EventUpdateDto dto){
        Event existing = getExisting(id);
        eventMapper.updateEventDto(dto, existing);

        log.info("Event updated with name {}", existing.getName());
        return eventMapper.toResponseDto(existing);
    }

    public void deleteEvent(UUID id){
        Event existing = getExisting(id);
        eventRepository.delete(existing);
        log.info("Event deleted with name {}", existing.getName());
    }

    @Transactional(readOnly = true)
    public EventResponseDto getEvent(UUID id){
        Event existing = getExisting(id);

        log.debug("Fetching event with id {}", existing.getId());
        return eventMapper.toResponseDto(existing);
    }

    @Transactional(readOnly = true)
    public List<EventResponseDto> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        log.debug("Fetching all events with size {}", events.size());
        return eventMapper.toResponseDtoList(events);
    }
}
