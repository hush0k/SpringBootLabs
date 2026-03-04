package kz.kbtu.tsis4.controller;

import jakarta.validation.Valid;
import kz.kbtu.tsis4.dto.request.EventCreateDto;
import kz.kbtu.tsis4.dto.request.EventUpdateDto;
import kz.kbtu.tsis4.dto.response.EventResponseDto;
import kz.kbtu.tsis4.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Validated
public class EventController {
    private final EventService eventService;

    //CREATE EVENT
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EventResponseDto createEvent(@Valid @RequestBody EventCreateDto dto){
        return eventService.create(dto);
    }

    //PATCH EVENT
    @PatchMapping("/{id}")
    public EventResponseDto updateEvent(@PathVariable UUID id, @Valid @RequestBody EventUpdateDto dto){
        return eventService.updateEvent(id, dto);
    }

    //DELETE EVENT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable UUID id){
        eventService.deleteEvent(id);
    }

    //GET EVENT BY ID
    @GetMapping("/{id}")
    public EventResponseDto getEventById(@PathVariable UUID id){
        return eventService.getEvent(id);
    }

    //GET ALL EVENTS
    @GetMapping
    public List<EventResponseDto> getAllEvents(){
        return eventService.getAllEvents();
    }
}
