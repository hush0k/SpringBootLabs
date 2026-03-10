package kz.kbtu.tsis5v2.service;

import kz.kbtu.tsis5v2.domain.Subject;
import kz.kbtu.tsis5v2.dto.requests.SubjectCreateDto;
import kz.kbtu.tsis5v2.dto.requests.SubjectUpdateDto;
import kz.kbtu.tsis5v2.dto.responses.SubjectResponseDto;
import kz.kbtu.tsis5v2.exceptions.handleNotFound;
import kz.kbtu.tsis5v2.mapper.SubjectMapper;
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
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Transactional(readOnly = true)
    public Subject getExisting(String id) {
        return subjectRepository.findById(id).orElseThrow(() -> {
            log.warn("Subject not found with id {}", id);
            return new handleNotFound("Subject with id: " + id + " not found");
        });
    }

    public SubjectResponseDto create(SubjectCreateDto dto) {
        Subject subject = subjectMapper.toSubject(dto);
        if (dto.prerequisiteIds() != null) {
            dto.prerequisiteIds().forEach(prereqId -> {
                if (!subjectRepository.existsById(prereqId)) {
                    throw new handleNotFound("Prerequisite subject with id: " + prereqId + " not found");
                }
            });
            subject.setPrerequisites(dto.prerequisiteIds());
        }
        Subject saved = subjectRepository.save(subject);
        log.info("Subject created with id {}, name {}", saved.getId(), saved.getName());
        return subjectMapper.toSubjectDto(saved);
    }

    public SubjectResponseDto update(String id, SubjectUpdateDto dto) {
        Subject existing = getExisting(id);
        subjectMapper.updateSubjectFromDto(dto, existing);
        if (dto.prerequisiteIds() != null) {
            dto.prerequisiteIds().forEach(prereqId -> {
                if (!subjectRepository.existsById(prereqId)) {
                    throw new handleNotFound("Prerequisite subject with id: " + prereqId + " not found");
                }
            });
            existing.setPrerequisites(dto.prerequisiteIds());
        }
        log.info("Subject updated with id {}", existing.getId());
        return subjectMapper.toSubjectDto(existing);
    }

    public void delete(String id) {
        Subject existing = getExisting(id);
        subjectRepository.delete(existing);
        log.info("Subject deleted with id {}, name {}", existing.getId(), existing.getName());
    }

    @Transactional(readOnly = true)
    public Page<SubjectResponseDto> getAllSubjects(Pageable pageable) {
        return subjectRepository.findAll(pageable).map(subjectMapper::toSubjectDto);
    }

    @Transactional(readOnly = true)
    public SubjectResponseDto getSubjectById(String id) {
        Subject subject = getExisting(id);
        log.debug("Fetching subject with id {}", id);
        return subjectMapper.toSubjectDto(subject);
    }

    @Transactional(readOnly = true)
    public Subject getById(String id) {
        return getExisting(id);
    }
}
