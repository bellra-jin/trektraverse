package parkjinhee.projecttrektraverse.post.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostResponseDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-01T00:08:11+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PostResponseMapperImpl implements PostResponseMapper {

    @Override
    public Post postResponseDTOToPost(PostResponseDto postResponseDto) {
        if ( postResponseDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( postResponseDto.getId() );
        post.setPostTitle( postResponseDto.getPostTitle() );
        post.setPostContent( postResponseDto.getPostContent() );
        post.setPostWriter( postResponseDto.getPostWriter() );
        post.setBoard( postResponseDto.getBoard() );
        post.setRegion( postResponseDto.getRegion() );
        post.setTheme( postResponseDto.getTheme() );

        return post;
    }
}
