package parkjinhee.projecttrektraverse.board.entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import parkjinhee.projecttrektraverse.post.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@EntityListeners({AuditingEntityListener.class})
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String boardTitle;

    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "board")
    private final List<Post> posts = new ArrayList();

    public Board(String boardTitle){
        this.boardTitle=boardTitle;
    }


}
