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

@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String themeTitle;

    @Column(nullable = false, length = 6)
    private String themePw;

    @CreatedDate
    @Column(name="created_at",nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "theme")
    private final List<Post> posts = new ArrayList<>();

    public Theme(String themeTitle, String themePw){

        this.themeTitle=themeTitle;
        this.themePw= themePw;
    }

//    public static ThemeBuilder builder() {
//        return new ThemeBuilder();
//    }
//
//    public ThemeBuilder toBuilder() {
//        return (new ThemeBuilder()).id(this.id).themeTitle(this.themeTitle).themePw(this.themePw).createdAt(this.createdAt);
//    }
//
//    public Long getId() {
//        return this.id;
//    }
//
//    public String getThemeTitle() {
//        return this.themeTitle;
//    }
//
//    public String getThemePw() {
//        return this.themePw;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return this.createdAt;
//    }
//
//    public List<Post> getPosts() {
//        return this.posts;
//    }
//
//    public Theme() {
//    }
//
//    public Theme(final Long id, final String themeTitle, final String themePw, final LocalDateTime createdAt) {
//        this.id = id;
//        this.themeTitle = themeTitle;
//        this.themePw = themePw;
//        this.createdAt = createdAt;
//    }
//
//    public static class ThemeBuilder {
//        private Long id;
//        private String themeTitle;
//        private String themePw;
//        private LocalDateTime createdAt;
//
//        ThemeBuilder() {
//        }
//
//        public ThemeBuilder id(final Long id) {
//            this.id = id;
//            return this;
//        }
//
//        public ThemeBuilder themeTitle(final String themeTitle) {
//            this.themeTitle = themeTitle;
//            return this;
//        }
//
//        public ThemeBuilder themePw(final String themePw) {
//            this.themePw = themePw;
//            return this;
//        }
//
//        public ThemeBuilder createdAt(final LocalDateTime createdAt) {
//            this.createdAt = createdAt;
//            return this;
//        }
//
//        public Theme build() {
//            return new Theme(this.id, this.themeTitle, this.themePw, this.createdAt);
//        }
//
//        public String toString() {
//            return "Board.BoardBuilder(id=" + this.id + ", title=" + this.themePw + ", password=" + this.themePw + ", createdAt=" + this.createdAt + ")";
//        }
//    }



}


