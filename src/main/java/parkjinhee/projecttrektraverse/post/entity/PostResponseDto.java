package parkjinhee.projecttrektraverse.post.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.region.entity.Region;
import parkjinhee.projecttrektraverse.theme.entity.Theme;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String postTitle;
    private String postContent;
    private String postWriter;
    private Board board;
    private Region region;
    private Theme theme;


}
