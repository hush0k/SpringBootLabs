package kz.kbtu.tsis6.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="feed_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "post_id", nullable = false)
    private UUID postId;

    @Column(name = "producer_id", nullable = false)
    private UUID producerId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "hashtags")
    private String hashtags;

    @Column(name = "received_at", nullable = false)
    private LocalDateTime receivedAt;

}
