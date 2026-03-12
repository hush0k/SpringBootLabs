package kz.kbtu.tsis6.repository;

import kz.kbtu.tsis6.domain.FeedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeedItemRepository extends JpaRepository<FeedItem, UUID> {
    List<FeedItem> findByProducerId(UUID producerId);
}
