package kz.kbtu.tsis6.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.kbtu.tsis6.dto.request.ProducerCreateDto;
import kz.kbtu.tsis6.dto.response.ProducerResponseDto;
import kz.kbtu.tsis6.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/producers")
@RequiredArgsConstructor
@Validated
public class ProducerController {

    private final ProducerService producerService;


    @Operation(
            summary = "Create Producer",
            description = "Create a new producer"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producer created"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProducerResponseDto createProducer(@Valid @RequestBody ProducerCreateDto dto) {
        return producerService.create(dto);
    }


    @Operation(
            summary = "Get All Producers",
            description = "Returns all producers"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producers retrieved")
    })
    @GetMapping
    public List<ProducerResponseDto> getProducers() {
        return producerService.getAll();
    }


    @Operation(
            summary = "Get Producer by ID",
            description = "Returns a producer by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producer retrieved"),
            @ApiResponse(responseCode = "404", description = "Producer not found")
    })
    @GetMapping("/{id}")
    public ProducerResponseDto getProducer(@PathVariable UUID id) {
        return producerService.getById(id);
    }
}