package kz.kbtu.tsis6.service;

import kz.kbtu.tsis6.domain.Producer;
import kz.kbtu.tsis6.dto.request.ProducerCreateDto;
import kz.kbtu.tsis6.dto.response.ProducerResponseDto;
import kz.kbtu.tsis6.exeptions.NotFoundException;
import kz.kbtu.tsis6.mapper.ProducerMapper;
import kz.kbtu.tsis6.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final ProducerMapper producerMapper;

    public Producer getExisted(UUID id) {
        return producerRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Producer with {} id not fount", id);
                    return new NotFoundException("Producer with id " + id + " not found");
                });
    }

    public ProducerResponseDto create(ProducerCreateDto dto){
        Producer producer = producerMapper.toProducer(dto);
        Producer saved = producerRepository.save(producer);

        log.info("Created producer with id {}", saved.getId());
        return producerMapper.toProducerResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public ProducerResponseDto getById(UUID id){
        Producer producer = getExisted(id);

        log.debug("Found producer with id {}", id);
        return producerMapper.toProducerResponseDto(producer);
    }

    @Transactional(readOnly = true)
    public List<ProducerResponseDto> getAll(){
        List<Producer> producers = producerRepository.findAll();

        log.debug("Found {} producers", producers.size());
        return producerMapper.toProducerResponseAll(producers);
    }
}
