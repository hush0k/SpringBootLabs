package kz.kbtu.tsis5v2.service;

import kz.kbtu.tsis5v2.domain.Student;
import kz.kbtu.tsis5v2.dto.requests.StudentCreateDto;
import kz.kbtu.tsis5v2.dto.requests.StudentUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.StudentResponseDto;
import kz.kbtu.tsis5v2.exceptions.handleNotFound;
import kz.kbtu.tsis5v2.mapper.StudentMapper;
import kz.kbtu.tsis5v2.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    private Student getExisting(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            log.warn("Student not found with id {}", id);
            return new handleNotFound("Student with id: " + id + " not found");
        });
    }

    public StudentResponseDto create(StudentCreateDto dto) {
        Student student = studentMapper.toStudent(dto);
        Student saved = studentRepository.save(student);
        log.info("Student created with id {}, email {}", saved.getId(), saved.getEmail());
        return studentMapper.toStudentDto(saved);
    }

    public StudentResponseDto update(Long id, StudentUpdateDto dto) {
        Student existing = getExisting(id);
        studentMapper.updateStudentFromDto(dto, existing);
        log.info("Student updated with id {}", existing.getId());
        return studentMapper.toStudentDto(existing);
    }

    public void delete(Long id) {
        Student existing = getExisting(id);
        studentRepository.delete(existing);
        log.info("Student deleted with id {}", id);
    }

    @Transactional(readOnly = true)
    public StudentResponseDto getStudentById(Long id) {
        Student student = getExisting(id);
        log.debug("Fetching student with id {}", id);
        return studentMapper.toStudentDto(student);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponseDto> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).map(studentMapper::toStudentDto);
    }

    @Transactional(readOnly = true)
    public Student getById(Long id) {
        return getExisting(id);
    }
}
