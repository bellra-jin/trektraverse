package parkjinhee.projecttrektraverse.post.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-22T19:58:56+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post postDTOToPost(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setPostTitle( postDto.getPostTitle() );
        post.setPostContent( postDto.getPostContent() );
        post.setPostWriter( postDto.getPostWriter() );
        post.setPostPw( postDto.getPostPw() );
        post.setBoard( postDto.getBoard() );
        post.setRegion( postDto.getRegion() );
        post.setTheme( postDto.getTheme() );
        post.setPostLike( postDto.getPostLike() );
        post.setWatchCount( postDto.getWatchCount() );

        return post;
    }
}
