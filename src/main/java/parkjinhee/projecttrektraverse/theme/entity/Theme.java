package parkjinhee.projecttrektraverse.theme.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import parkjinhee.projecttrektraverse.post.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@Entity
@Builder
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 10)
    private String theme_title;

    @Column(nullable = false, length = 8)
    private String theme_pw;

    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime create_date;

    @OneToMany(
            mappedBy = "theme"
    )
    private final List<Post> posts = new ArrayList<>();


    @ColumnDefault("0")
    private int watch_count;
}
