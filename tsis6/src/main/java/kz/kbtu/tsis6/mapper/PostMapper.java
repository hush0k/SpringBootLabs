package kz.kbtu.tsis6.mapper;

import kz.kbtu.tsis6.domain.Post;
import kz.kbtu.tsis6.dto.request.PostCreateDto;
import kz.kbtu.tsis6.dto.response.PostResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    public Post toPost(PostCreateDto dto);

    public PostResponseDto toPostResponseDto(Post post);

    public List<PostResponseDto> toPPostResponseAll(List<Post> post);
}
