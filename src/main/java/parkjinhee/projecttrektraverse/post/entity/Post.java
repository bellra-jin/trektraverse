package parkjinhee.projecttrektraverse.post.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import parkjinhee.projecttrektraverse.global.entity.BaseEntity;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.theme.entity.Theme;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 30)
    private String post_title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String post_content;

    @Column(nullable = false, length = 10)
    private String post_writer;

    @Column(nullable = false, length = 8)
    private String post_pw;

    @ManyToOne
    @JoinColumn(name = "board_title", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "theme_title", nullable = true)
    private Theme theme;

    @Column(nullable = false)
    private String region;

    private int post_like;

    @ColumnDefault("0")
    private int watch_count;

}
