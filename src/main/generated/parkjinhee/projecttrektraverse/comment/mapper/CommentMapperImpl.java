package parkjinhee.projecttrektraverse.comment.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import parkjinhee.projecttrektraverse.comment.entity.Comment;
import parkjinhee.projecttrektraverse.comment.entity.CommentDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-28T02:13:55+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentDtoToComment(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCommentWriter( commentDto.getCommentWriter() );
        comment.setCommentPw( commentDto.getCommentPw() );
        comment.setCommentContent( commentDto.getCommentContent() );

        return comment;
    }
}
