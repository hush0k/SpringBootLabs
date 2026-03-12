package kz.kbtu.tsis6.kafka.event;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record PostCreatedEvent(
       UUID postId,
       UUID producerId,
       String content,
       Set<String> hashtags,
       LocalDateTime timestamp
) {}
