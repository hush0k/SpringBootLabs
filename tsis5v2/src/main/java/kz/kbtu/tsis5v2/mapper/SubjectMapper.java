package kz.kbtu.tsis5v2.mapper;

import kz.kbtu.tsis5v2.domain.Subject;
import kz.kbtu.tsis5v2.dto.requests.SubjectCreateDto;
import kz.kbtu.tsis5v2.dto.requests.SubjectUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.SubjectResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EnrollmentMapper.class, TeacherMapper.class})
public interface SubjectMapper {

    SubjectResponseDto toSubjectDto(Subject subject);

    Subject toSubject(SubjectCreateDto subjectCreateDto);

    List<SubjectResponseDto> toSubjectAllDto(List<Subject> subjects);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubjectFromDto(SubjectUpdateDto subjectUpdateDto, @MappingTarget Subject subject);
}