package parkjinhee.projecttrektraverse.comment.mapper;

import org.mapstruct.Mapper;
import parkjinhee.projecttrektraverse.comment.entity.Comment;
import parkjinhee.projecttrektraverse.comment.entity.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentDtoToComment(CommentDto commentDto);
}
