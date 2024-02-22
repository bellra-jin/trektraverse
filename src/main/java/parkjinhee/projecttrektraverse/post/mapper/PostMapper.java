package parkjinhee.projecttrektraverse.post.mapper;


import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postDTOToPost(PostDto postDto);
}
