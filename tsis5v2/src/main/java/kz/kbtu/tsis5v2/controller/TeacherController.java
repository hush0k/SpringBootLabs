package kz.kbtu.tsis5v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.kbtu.tsis5v2.dto.requests.TeacherCreateDto;
import kz.kbtu.tsis5v2.dto.requests.TeacherUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.TeacherResponseDto;
import kz.kbtu.tsis5v2.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/teachers")
@RestController
@RequiredArgsConstructor
@Validated
public class TeacherController {
    private final TeacherService teacherService;


    @Operation(
            summary = "Create Teacher",
            description = "Create a new teacher in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TeacherResponseDto createTeacher(@Valid @RequestBody TeacherCreateDto dto){
        return teacherService.create(dto);
    }


    @Operation(
            summary = "Delete Teacher",
            description = "Delete teacher from the system by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Teacher deleted"),
            @ApiResponse(responseCode = "404", description = "Teacher with ID Not Found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id){
        teacherService.delete(id);
    }


    @Operation(
            summary = "Patch Teacher",
            description = "Patch teacher with ID in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Request"),
            @ApiResponse(responseCode = "404", description = "Teacher with ID Not Found")
    })
    @PatchMapping("/{id}")
    public TeacherResponseDto updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherUpdateDto dto){
        return teacherService.update(id, dto);
    }


    @Operation(
            summary = "Get All Teachers",
            description = "Returns teachers with pagination. Query params: page (0..), size, sort=field,asc|desc"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teachers got")
    })
    @GetMapping(produces = {"application/json", "application/xml"})
    public Page<TeacherResponseDto> getAllTeachers(
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ){
        return teacherService.getAll(pageable);
    }


    @Operation(
            summary = "Get a Teacher",
            description = "Get a teacher with ID in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher got"),
            @ApiResponse(responseCode = "404", description = "Teacher with ID Not Found")
    })
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public TeacherResponseDto getTeacher(@PathVariable Long id){
        return teacherService.get(id);
    }
}
