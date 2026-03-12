package kz.kbtu.tsis6.kafka.producer;

import kz.kbtu.tsis6.kafka.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostKafkaProducer {

    private final KafkaTemplate<String, PostCreatedEvent> kafkaTemplate;

    public void sendPostCreateEvent(PostCreatedEvent event) {
            kafkaTemplate.send("posts", event.postId().toString(), event);
            log.info("Sent PostCreatedEvent for postId: {}", event.postId());
    }
}
