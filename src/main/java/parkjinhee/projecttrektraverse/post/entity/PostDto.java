package parkjinhee.projecttrektraverse.post.entity;

import lombok.Getter;
import lombok.Setter;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.global.entity.BaseEntity;
import parkjinhee.projecttrektraverse.theme.entity.Theme;

@Getter
@Setter
public class PostDto {
    private String postTitle;
    private String postContent;
    private String postWriter;
    private String postPw;
    private Board board;
    private String region;
    private Theme theme;
    private int postLike;
    private int watchCount;

    public PostDto(final String postTitle, final String postContent, final String postWriter, final String postPw, Board board, final String region, Theme theme, final int postLike, final int watchCount) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postWriter = postWriter;
        this.region=region;
        this.postPw = postPw;
        this.board = board;
        this.theme = theme;
        this.postLike = postLike;
        this.watchCount = watchCount;
    }

//    public Post postDTOToPost(PostDto postDto) {
//        if (postDto == null) {
//            return null;
//        } else {
//            Post post = new Post();
//            post.setPostTitle(postDto.getPostTitle());
//            post.setPostContent(postDto.getPostContent());
//            post.setPostWriter(postDto.getPostWriter());
//            post.setRegion(postDto.getRegion());
//            post.setBoard(postDto.getBoard());
//            post.setTheme(postDto.getTheme());
//            post.setPostLike(postDto.getPostLike());
//            post.setWatchCount(postDto.getWatchCount());
//            return post;
//        }
//    }



}
