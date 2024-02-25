package parkjinhee.projecttrektraverse.imgFile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.comment.entity.Comment;
import parkjinhee.projecttrektraverse.global.entity.BaseEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Img_file extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String file_name;

    @Column(nullable = false)
    private String file_url;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;
}
