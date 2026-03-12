package kz.kbtu.tsis6.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kz.kbtu.tsis6.domain.FeedItem;
import kz.kbtu.tsis6.repository.FeedItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedItemRepository feedItemRepository;

    @Operation(summary = "Get feed by producerId")
    @ApiResponse(responseCode = "200", description = "Feed retrieved")
    @GetMapping
    public List<FeedItem> getFeed(
            @Parameter(description = "Producer ID")
            @RequestParam UUID userId) {
        return feedItemRepository.findByProducerId(userId);
    }
}
