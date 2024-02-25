package parkjinhee.projecttrektraverse.post.mapper;


import org.mapstruct.Mapping;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postDTOToPost(PostDto postDto);
}

//@Mapper(componentModel = "spring")
//public interface PostMapper {
//    @Mapping(target = "postTitle", source = "postDto.postTitle")
//    @Mapping(target = "postContent", source = "postDto.postContent")
//    @Mapping(target = "postWriter", source = "postDto.postWriter")
//    @Mapping(target = "postPw", source = "postDto.postPw")
//    Post postDTOToPost(PostDto postDto);
//}