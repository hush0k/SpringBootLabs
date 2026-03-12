package kz.kbtu.tsis6.service;

import kz.kbtu.tsis6.domain.Post;
import kz.kbtu.tsis6.domain.Producer;
import kz.kbtu.tsis6.dto.request.PostCreateDto;
import kz.kbtu.tsis6.dto.response.PostResponseDto;
import kz.kbtu.tsis6.exeptions.NotFoundException;
import kz.kbtu.tsis6.kafka.event.PostCreatedEvent;
import kz.kbtu.tsis6.kafka.producer.PostKafkaProducer;
import kz.kbtu.tsis6.mapper.PostMapper;
import kz.kbtu.tsis6.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Validated
public class PostService {

    private final PostRepository postRepository;
    private final ProducerService producerService;
    private final PostMapper postMapper;
    private final PostKafkaProducer postKafkaProducer;

    public Post getExisted(UUID id){
        return postRepository.findById(id)
                .orElseThrow(() -> {
                            log.warn("Post with {} id is not found", id);
                            return new NotFoundException("Post with " + id + " id is not found");
                        }
                );
    }

    public PostResponseDto create(PostCreateDto dto) {
        Producer producer = producerService.getExisted(dto.producerId());

        Post post = postMapper.toPost(dto);
        post.setProducer(producer);

        Post saved = postRepository.save(post);

        PostCreatedEvent event = new PostCreatedEvent(
                saved.getId(),
                saved.getProducer().getId(),
                saved.getContent(),
                saved.getHashtags(),
                LocalDateTime.now()
        );
        postKafkaProducer.sendPostCreateEvent(event);

        log.info("Post with {} id created", saved.getId());
        return postMapper.toPostResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public PostResponseDto getById(UUID id) {
        Post post = getExisted(id);

        log.debug("Took post with {} id", post.getId());
        return postMapper.toPostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAll() {
        List<Post> posts = postRepository.findAll();

        log.info("Found {} posts", posts.size());
        return postMapper.toPPostResponseAll(posts);
    }
}
