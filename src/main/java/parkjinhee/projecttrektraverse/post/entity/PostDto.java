package parkjinhee.projecttrektraverse.post.entity;

import lombok.Getter;

@Getter
public class PostDto {
    private String post_title;
    private String post_content;
    private String post_writer;

    public PostDto(final String post_title, final String post_content, final String post_writer) {
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_writer = post_writer;
    }

}
