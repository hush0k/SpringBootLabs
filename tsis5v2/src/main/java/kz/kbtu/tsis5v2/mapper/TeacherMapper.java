package kz.kbtu.tsis5v2.mapper;

import kz.kbtu.tsis5v2.domain.Subject;
import kz.kbtu.tsis5v2.domain.Teacher;
import kz.kbtu.tsis5v2.dto.requests.TeacherCreateDto;
import kz.kbtu.tsis5v2.dto.requests.TeacherUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.SubjectShortDto;
import kz.kbtu.tsis5v2.dto.responses.TeacherResponseDto;
import kz.kbtu.tsis5v2.dto.responses.TeacherShortDto;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherResponseDto toTeacherDto(Teacher teacher);

    TeacherShortDto toTeacherShortDto(Teacher teacher);
    Set<TeacherShortDto> toTeacherShortDtoSet(Set<Teacher> teachers);

    SubjectShortDto toSubjectShortDto(Subject subject);
    Set<SubjectShortDto> toSubjectShortDtoSet(Set<Subject> subjects);

    Teacher toTeacher(TeacherCreateDto teacherCreateDto);

    List<TeacherResponseDto> toTeacherDtoList(List<Teacher> teachers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromDto(TeacherUpdateDto teacherUpdateDto, @MappingTarget Teacher teacher);
}