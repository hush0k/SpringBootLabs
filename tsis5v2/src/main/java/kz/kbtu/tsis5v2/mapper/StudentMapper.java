package kz.kbtu.tsis5v2.mapper;

import kz.kbtu.tsis5v2.domain.Student;
import kz.kbtu.tsis5v2.dto.requests.StudentCreateDto;
import kz.kbtu.tsis5v2.dto.requests.StudentUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.StudentResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EnrollmentMapper.class})
public interface StudentMapper {

    StudentResponseDto toStudentDto(Student student);

    Student toStudent(StudentCreateDto studentCreateDto);

    List<StudentResponseDto> toStudentAllDto(List<Student> students);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(StudentUpdateDto dto, @MappingTarget Student student);
}