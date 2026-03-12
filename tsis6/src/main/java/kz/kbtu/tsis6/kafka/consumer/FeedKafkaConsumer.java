package kz.kbtu.tsis6.kafka.consumer;

import kz.kbtu.tsis6.domain.FeedItem;
import kz.kbtu.tsis6.kafka.event.PostCreatedEvent;
import kz.kbtu.tsis6.repository.FeedItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class FeedKafkaConsumer {

    private final FeedItemRepository feedItemRepository;

    @KafkaListener(topics = "posts", groupId = "feed-group")
    public void listen(PostCreatedEvent event){

        if (event.content() == null || event.content().isBlank()) {
            log.warn("Skipping event {} as content is empty", event.postId());
            return;
        }

        log.info("Adding post {} by user {} to follower feeds - '{}'",
                event.postId(), event.producerId(), event.content()
        );

        FeedItem feedItem = FeedItem.builder()
                .postId(event.postId())
                .producerId(event.producerId())
                .content(event.content())
                .hashtags(event.hashtags() != null
                        ? String.join(",", event.hashtags())
                        : null)
                .receivedAt(event.timestamp())
                .build();

        feedItemRepository.save(feedItem);
    }

}
