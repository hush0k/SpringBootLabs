package kz.kbtu.tsis5v2.mapper;

import kz.kbtu.tsis5v2.domain.Enrollment;
import kz.kbtu.tsis5v2.dto.requests.EnrollmentUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.EnrollmentResponseDto;
import kz.kbtu.tsis5v2.dto.responses.EnrollmentShortDto;
import kz.kbtu.tsis5v2.dto.responses.StudentShortDto;
import kz.kbtu.tsis5v2.dto.responses.SubjectShortDto;
import kz.kbtu.tsis5v2.domain.Student;
import kz.kbtu.tsis5v2.domain.Subject;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(target = "student", source = "student")
    @Mapping(target = "subject", source = "subject")
    EnrollmentResponseDto toEnrollmentDto(Enrollment enrollment);

    List<EnrollmentResponseDto> toEnrollmentAllDto(List<Enrollment> enrollments);

    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "subjectName", source = "subject.name")
    EnrollmentShortDto toEnrollmentShortDto(Enrollment enrollment);

    Set<EnrollmentShortDto> toEnrollmentShortDtoSet(Set<Enrollment> enrollments);

    StudentShortDto toStudentShortDto(Student student);
    SubjectShortDto toSubjectShortDto(Subject subject);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void
    updateEnrollmentFromDto(EnrollmentUpdateDto dto, @MappingTarget Enrollment enrollment);
}