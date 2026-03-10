package kz.kbtu.tsis5v2.service;

import kz.kbtu.tsis5v2.domain.Enrollment;
import kz.kbtu.tsis5v2.domain.Student;
import kz.kbtu.tsis5v2.domain.Subject;
import kz.kbtu.tsis5v2.dto.requests.EnrollmentCreateDto;
import kz.kbtu.tsis5v2.dto.requests.EnrollmentUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.EnrollmentResponseDto;
import kz.kbtu.tsis5v2.exceptions.handleNotFound;
import kz.kbtu.tsis5v2.mapper.EnrollmentMapper;
import kz.kbtu.tsis5v2.repository.EnrollmentRepository;
import kz.kbtu.tsis5v2.repository.StudentRepository;
import kz.kbtu.tsis5v2.repository.SubjectRepository;
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
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final EnrollmentMapper enrollmentMapper;

    private Enrollment getExisting(Long id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> {
            log.warn("Enrollment not found with id {}", id);
            return new handleNotFound("Enrollment with id: " + id + " not found");
        });
    }

    public EnrollmentResponseDto create(EnrollmentCreateDto dto) {
        Student student = studentRepository.findById(dto.studentId())
                .orElseThrow(() -> new handleNotFound("Student with id: " + dto.studentId() + " not found"));
        Subject subject = subjectRepository.findById(dto.subjectId())
                .orElseThrow(() -> new handleNotFound("Subject with id: " + dto.subjectId() + " not found"));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .subject(subject)
                .semester(dto.semester())
                .grade(dto.grade())
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);
        log.info("Enrollment created with id {}, studentId {}, subjectId {}", saved.getId(), dto.studentId(), dto.subjectId());
        return enrollmentMapper.toEnrollmentDto(saved);
    }

    public EnrollmentResponseDto update(Long id, EnrollmentUpdateDto dto) {
        Enrollment existing = getExisting(id);

        Student student = studentRepository.findById(dto.studentId())
                .orElseThrow(() -> new handleNotFound("Student with id: " + dto.studentId() + " not found"));
        Subject subject = subjectRepository.findById(dto.subjectId())
                .orElseThrow(() -> new handleNotFound("Subject with id: " + dto.subjectId() + " not found"));

        existing.setStudent(student);
        existing.setSubject(subject);
        existing.setSemester(dto.semester());
        existing.setGrade(dto.grade());

        log.info("Enrollment updated with id {}", existing.getId());
        return enrollmentMapper.toEnrollmentDto(existing);
    }

    public void delete(Long id) {
        Enrollment existing = getExisting(id);
        enrollmentRepository.delete(existing);
        log.info("Enrollment deleted with id {}", id);
    }

    @Transactional(readOnly = true)
    public EnrollmentResponseDto getEnrollmentById(Long id) {
        Enrollment enrollment = getExisting(id);
        log.debug("Fetching enrollment with id {}", id);
        return enrollmentMapper.toEnrollmentDto(enrollment);
    }

    @Transactional(readOnly = true)
    public Page<EnrollmentResponseDto> getAllEnrollments(Pageable pageable) {
        return enrollmentRepository.findAll(pageable).map(enrollmentMapper::toEnrollmentDto);
    }

    @Transactional(readOnly = true)
    public Enrollment getById(Long id) {
        return getExisting(id);
    }
}
