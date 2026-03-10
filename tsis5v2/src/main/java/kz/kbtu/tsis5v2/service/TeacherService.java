package kz.kbtu.tsis5v2.service;

import kz.kbtu.tsis5v2.domain.Subject;
import kz.kbtu.tsis5v2.domain.Teacher;
import kz.kbtu.tsis5v2.dto.requests.TeacherCreateDto;
import kz.kbtu.tsis5v2.dto.requests.TeacherUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.TeacherResponseDto;
import kz.kbtu.tsis5v2.exceptions.handleNotFound;
import kz.kbtu.tsis5v2.mapper.TeacherMapper;
import kz.kbtu.tsis5v2.repository.SubjectRepository;
import kz.kbtu.tsis5v2.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherMapper teacherMapper;

    private Teacher getExisting(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Teacher not found with id {}", id);
                    return new handleNotFound("Teacher with id: " + id + " not found");
                });
    }

    public TeacherResponseDto create(TeacherCreateDto dto) {
        Teacher teacher = teacherMapper.toTeacher(dto);

        if (dto.subjectIds() != null && !dto.subjectIds().isEmpty()) {
            Set<Subject> subjects = dto.subjectIds().stream()
                    .map(code -> subjectRepository.findById(code)
                            .orElseThrow(() -> new handleNotFound("Subject with id: " + code + " not found")))
                    .collect(Collectors.toSet());
            teacher.setSubjects(subjects);
        }

        Teacher saved = teacherRepository.save(teacher);
        log.info("Teacher created with id {}, email {}", saved.getId(), saved.getEmail());
        return teacherMapper.toTeacherDto(saved);
    }

    public TeacherResponseDto update(Long id, TeacherUpdateDto dto) {
        Teacher existing = getExisting(id);

        teacherMapper.updateTeacherFromDto(dto, existing);

        if (dto.subjectIds() != null) {
            Set<Subject> subjects = dto.subjectIds().stream()
                    .map(code -> subjectRepository.findById(code)
                            .orElseThrow(() -> new handleNotFound("Subject with id: " + code + " not found")))
                    .collect(Collectors.toSet());
            existing.setSubjects(subjects);
        }

        log.info("Teacher updated with id {}", existing.getId());
        return teacherMapper.toTeacherDto(existing);
    }

    public void delete(Long id) {
        Teacher existing = getExisting(id);
        teacherRepository.delete(existing);
        log.info("Teacher deleted with id {}", id);
    }

    @Transactional(readOnly = true)
    public TeacherResponseDto get(Long id) {
        Teacher existing = getExisting(id);
        log.debug("Fetching teacher with id {}", id);
        return teacherMapper.toTeacherDto(existing);
    }

    @Transactional(readOnly = true)
    public Page<TeacherResponseDto> getAll(Pageable pageable) {
        return teacherRepository.findAll(pageable).map(teacherMapper::toTeacherDto);
    }

    @Transactional(readOnly = true)
    public Teacher getById(Long id) {
        return getExisting(id);
    }
}
