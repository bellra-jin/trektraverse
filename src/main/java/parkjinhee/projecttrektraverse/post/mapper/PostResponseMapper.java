package parkjinhee.projecttrektraverse.post.mapper;

import org.mapstruct.Mapper;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostResponseDto;

@Mapper(componentModel = "spring")
public interface PostResponseMapper {
    Post postResponseDTOToPost(PostResponseDto postResponseDto);
}
