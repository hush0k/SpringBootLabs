package kz.kbtu.tsis6.mapper;

import kz.kbtu.tsis6.domain.Producer;
import kz.kbtu.tsis6.dto.request.ProducerCreateDto;
import kz.kbtu.tsis6.dto.response.ProducerResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    public Producer toProducer(ProducerCreateDto dto);

    public ProducerResponseDto toProducerResponseDto(Producer producer);

    public List<ProducerResponseDto> toProducerResponseAll(List<Producer> producer);

}
