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
    private Board board;
    private Region region;
    private Theme theme;
//    private int postLike;
//    private int watchCount;

    //final int postLike, final int watchCount 추후 삽입
        public PostDto( final String postTitle, final String postContent, final String postWriter, final String postPw, final Board board, final Region region, final Theme theme) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postWriter = postWriter;
        this.region=region;
        this.postPw = postPw;
        this.board = board;
        this.theme = theme;
//        this.postLike = postLike;
//        this.watchCount = watchCount;
    }

    public PostDto() {}





}
