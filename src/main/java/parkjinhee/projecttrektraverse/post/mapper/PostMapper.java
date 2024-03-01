package parkjinhee.projecttrektraverse.post.mapper;


import org.mapstruct.Mapping;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostDto;
import org.mapstruct.Mapper;
import parkjinhee.projecttrektraverse.post.entity.PostPwDto;
import parkjinhee.projecttrektraverse.post.entity.PostResponseDto;


@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postDTOToPost(PostDto postDto);
    Post postPwDto(PostPwDto postPwDto);

}