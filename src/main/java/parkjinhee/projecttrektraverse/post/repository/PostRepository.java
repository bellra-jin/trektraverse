package parkjinhee.projecttrektraverse.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.theme.entity.Theme;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByBoardOrderByCreatedAtDesc(Board board, Pageable pageable);
    Page<Post> findAllByBoardAndPostTitleContaining(Board board, String keyword, Pageable pageable);

    Page<Post> findAllByThemeOrderByCreatedAtDesc(Theme theme, Pageable pageable);
    Page<Post> findAllByTheme(Theme theme, Pageable pageable);
    Page<Post> findAllByThemeAndPostTitleContaining(Theme theme, String keyword, Pageable pageable);

    List<Post> findByBoardIdAndThemeId(Long boardId, Long themeId);
}
