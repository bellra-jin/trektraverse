package parkjinhee.projecttrektraverse.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import parkjinhee.projecttrektraverse.global.entity.BaseEntity;
import parkjinhee.projecttrektraverse.post.entity.Post;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false, length = 10)
    private String commentWriter;

    @Column(nullable = false, length = 8)
    private String commentPw;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentContent;

    @Column(nullable = false)
    private int commentLike;
}
