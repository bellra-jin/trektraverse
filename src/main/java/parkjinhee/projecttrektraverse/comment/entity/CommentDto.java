package parkjinhee.projecttrektraverse.comment.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {
    private String commentWriter;
    private String commentContent;
    private String commentPw;
}
