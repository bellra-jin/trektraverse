package parkjinhee.projecttrektraverse.post.mapper;

import org.mapstruct.Mapper;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostPwDto;

@Mapper(componentModel = "spring")
public interface PostPwMapper {
    Post postPwDTOToPost(PostPwDto postPwDto);
}
