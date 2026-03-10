package kz.kbtu.tsis5v2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.kbtu.tsis5v2.dto.requests.EnrollmentCreateDto;
import kz.kbtu.tsis5v2.dto.requests.EnrollmentUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.EnrollmentResponseDto;
import kz.kbtu.tsis5v2.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/enrollments")
@RestController
@RequiredArgsConstructor
@Validated
public class EnrollmentController {
    private final EnrollmentService enrollmentService;


    @Operation(
            summary = "Create Enrollment",
            description = "Create a new enrollment in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enrollment created"),
            @ApiResponse(responseCode = "400", description = "Invalid Request"),
            @ApiResponse(responseCode = "404", description = "Student or Subject Not Found")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EnrollmentResponseDto createEnrollment(@Valid @RequestBody EnrollmentCreateDto dto) {
        return enrollmentService.create(dto);
    }


    @Operation(
            summary = "Delete Enrollment",
            description = "Delete enrollment from the system by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Enrollment deleted"),
            @ApiResponse(responseCode = "404", description = "Enrollment with ID Not Found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.delete(id);
    }


    @Operation(
            summary = "Update Enrollment",
            description = "Update enrollment with ID in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollment updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Request"),
            @ApiResponse(responseCode = "404", description = "Enrollment, Student or Subject Not Found")
    })
    @PutMapping("/{id}")
    public EnrollmentResponseDto updateEnrollment(@PathVariable Long id, @Valid @RequestBody EnrollmentUpdateDto dto) {
        return enrollmentService.update(id, dto);
    }


    @Operation(
            summary = "Get All Enrollments",
            description = "Returns enrollments with pagination. Query params: page (0..), size, sort=field,asc|desc"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollments got")
    })
    @GetMapping(produces = {"application/json", "application/xml"})
    public Page<EnrollmentResponseDto> getAllEnrollments(
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return enrollmentService.getAllEnrollments(pageable);
    }


    @Operation(
            summary = "Get an Enrollment",
            description = "Get an enrollment with ID in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollment got"),
            @ApiResponse(responseCode = "404", description = "Enrollment with ID Not Found")
    })
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public EnrollmentResponseDto getEnrollment(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }
}