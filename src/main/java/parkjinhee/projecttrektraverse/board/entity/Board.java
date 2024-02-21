package parkjinhee.projecttrektraverse.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import parkjinhee.projecttrektraverse.post.entity.Post;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String board_title;

    @OneToMany(mappedBy = "board")
    private final List<Post> posts = new ArrayList();
}
