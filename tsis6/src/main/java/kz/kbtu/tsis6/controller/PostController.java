package kz.kbtu.tsis6.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.kbtu.tsis6.dto.request.PostCreateDto;
import kz.kbtu.tsis6.dto.response.PostResponseDto;
import kz.kbtu.tsis6.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Validated
public class PostController {

    private final PostService postService;


    @Operation(
            summary = "Create Post",
            description = "Create a new post"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created"),
            @ApiResponse(responseCode = "400", description = "Invalid Request")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PostResponseDto createPost(@Valid @RequestBody PostCreateDto dto) {
        return postService.create(dto);
    }


    @Operation(
            summary = "Get All Posts",
            description = "Returns all posts"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts retrieved")
    })
    @GetMapping
    public List<PostResponseDto> getPosts() {
        return postService.getAll();
    }


    @Operation(
            summary = "Get Post by ID",
            description = "Returns a post by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post retrieved"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable UUID id) {
        return postService.getById(id);
    }
}