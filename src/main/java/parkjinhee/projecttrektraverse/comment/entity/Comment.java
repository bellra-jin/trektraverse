package parkjinhee.projecttrektraverse.comment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long post_comment_id;
    private String comment_writer;
    private String comment_pw;
    private String comment_content;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private LocalDateTime delete_date;
    private int comment_like;
}
