package kz.kbtu.tsis6.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kz.kbtu.tsis6.domain.FeedItem;
import kz.kbtu.tsis6.repository.FeedItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedItemRepository feedItemRepository;

    @Operation(summary = "Get feed by producerId")
    @ApiResponse(responseCode = "200", description = "Feed retrieved")
    @GetMapping("/{id}")
    public List<FeedItem> getFeed(
            @Parameter(description = "Producer ID")
            @PathVariable UUID id) {
        return feedItemRepository.findByProducerId(id);
    }
}
