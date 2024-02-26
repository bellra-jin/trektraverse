package parkjinhee.projecttrektraverse.post.entity;

import lombok.Getter;
import lombok.Setter;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.global.entity.BaseEntity;
import parkjinhee.projecttrektraverse.region.entity.Region;
import parkjinhee.projecttrektraverse.theme.entity.Theme;

import java.util.Optional;


@Getter
@Setter
public class PostDto {
    private String postTitle;
    private String postContent;
    private String postWriter;
    private String postPw;
    private Long boardId;
    private Long regionId;
    private Long themeId;
//    private int postLike;
//    private int watchCount;

    //final int postLike, final int watchCount 추후 삽입
        public PostDto( final String postTitle, final String postContent, final String postWriter, final String postPw, final Long boardId, final Long regionId, final Long themeId) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postWriter = postWriter;
        this.regionId=regionId;
        this.postPw = postPw;
        this.boardId = boardId;
        this.themeId = themeId;
//        this.postLike = postLike;
//        this.watchCount = watchCount;
    }

    public PostDto() {}





}
