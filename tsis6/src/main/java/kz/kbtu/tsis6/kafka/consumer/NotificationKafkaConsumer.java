package kz.kbtu.tsis6.kafka.consumer;

import kz.kbtu.tsis6.domain.Notification;
import kz.kbtu.tsis6.kafka.event.PostCreatedEvent;
import kz.kbtu.tsis6.repository.FeedItemRepository;
import kz.kbtu.tsis6.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationKafkaConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "posts", groupId = "notification-group")
    public void listen(PostCreatedEvent event) {

        log.info("Sending push notification to followers of user {} - new post {}", event.producerId(), event.postId());

        Notification notification = Notification.builder()
                .postId(event.postId())
                .producerId(event.producerId())
                .message("New post from user " + event.producerId() + ": " + event.content())
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}
