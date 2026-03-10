package kz.kbtu.tsis5v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.kbtu.tsis5v2.dto.requests.SubjectCreateDto;
import kz.kbtu.tsis5v2.dto.requests.SubjectUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.SubjectResponseDto;
import kz.kbtu.tsis5v2.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/subjects")
@RestController
@RequiredArgsConstructor
@Validated
public class SubjectController {
    private final SubjectService subjectService;


    @Operation(
            summary = "Create Subject",
            description = "Create a new subject in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subject created"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SubjectResponseDto createSubject(@Valid @RequestBody SubjectCreateDto dto) {
        return subjectService.create(dto);
    }


    @Operation(
            summary = "Delete Subject",
            description = "Delete subject from the system by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subject deleted"),
            @ApiResponse(responseCode = "404", description = "Subject with ID Not Found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable String id) {
        subjectService.delete(id);
    }


    @Operation(
            summary = "Patch Subject",
            description = "Patch subject with ID in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Request"),
            @ApiResponse(responseCode = "404", description = "Subject with ID Not Found")
    })
    @PatchMapping("/{id}")
    public SubjectResponseDto updateSubject(@PathVariable String id, @Valid @RequestBody SubjectUpdateDto dto) {
        return subjectService.update(id, dto);
    }


    @Operation(
            summary = "Get All Subjects",
            description = "Returns subjects with pagination. Query params: page (0..), size, sort=field,asc|desc"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subjects got")
    })
    @GetMapping(produces = {"application/json", "application/xml"})
    public Page<SubjectResponseDto> getAllSubjects(
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return subjectService.getAllSubjects(pageable);
    }


    @Operation(
            summary = "Get a Subject",
            description = "Get a subject with ID in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject got"),
            @ApiResponse(responseCode = "404", description = "Subject with ID Not Found")
    })
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public SubjectResponseDto getSubject(@PathVariable String id) {
        return subjectService.getSubjectById(id);
    }
}