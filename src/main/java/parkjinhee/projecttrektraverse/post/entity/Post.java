package parkjinhee.projecttrektraverse.post.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import parkjinhee.projecttrektraverse.global.entity.BaseEntity;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.theme.entity.Theme;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String postTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String postContent;

    @Column(nullable = false, length = 10)
    private String postWriter;

    @Column(nullable = false, length = 8)
    private String postPw;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private String region;

    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = true)
    private Theme theme;

    @Column(nullable = true)
    private int postLike;

    @ColumnDefault("0")
    private int watchCount;

    public Post(String title, String contnet, String writer, String password, Board board, String region) {
        this.postTitle=title;
        this.postContent=contnet;
        this.postWriter=writer;
        this.postPw=password;
        this.board=board;
        this.region=region;
    }
}
