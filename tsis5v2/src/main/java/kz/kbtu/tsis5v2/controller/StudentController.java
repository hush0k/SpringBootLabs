package kz.kbtu.tsis5v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.kbtu.tsis5v2.dto.requests.StudentCreateDto;
import kz.kbtu.tsis5v2.dto.requests.StudentUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.StudentResponseDto;
import kz.kbtu.tsis5v2.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/students")
@RestController
@RequiredArgsConstructor
@Validated
public class StudentController {
    private final StudentService studentService;


    @Operation(
            summary = "Create Student",
            description = "Create a new student in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StudentResponseDto createStudent(@Valid @RequestBody StudentCreateDto dto) {
        return studentService.create(dto);
    }


    @Operation(
            summary = "Delete Student",
            description = "Delete student from the system by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted"),
            @ApiResponse(responseCode = "404", description = "Student with ID Not Found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
    }


    @Operation(
            summary = "Patch Student",
            description = "Patch student with ID in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Request"),
            @ApiResponse(responseCode = "404", description = "Student with ID Not Found")
    })
    @PatchMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id, @Valid @RequestBody StudentUpdateDto dto) {
        return studentService.update(id, dto);
    }


    @Operation(
            summary = "Get All Students",
            description = "Returns students with pagination. Query params: page (0..), size, sort=field,asc|desc"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students got")
    })
    @GetMapping(produces = {"application/json", "application/xml"})
    public Page<StudentResponseDto> getAllStudents(
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return studentService.getAllStudents(pageable);
    }


    @Operation(
            summary = "Get a Student",
            description = "Get a student with ID in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student got"),
            @ApiResponse(responseCode = "404", description = "Student with ID Not Found")
    })
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public StudentResponseDto getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
}