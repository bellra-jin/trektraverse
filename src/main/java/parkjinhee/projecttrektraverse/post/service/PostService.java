package parkjinhee.projecttrektraverse.post.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.service.BoardService;
import parkjinhee.projecttrektraverse.global.exception.ExceptionCode;
import parkjinhee.projecttrektraverse.global.exception.ServiceLogicException;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.repository.PostRepository;
import parkjinhee.projecttrektraverse.region.entity.Region;
import parkjinhee.projecttrektraverse.region.service.RegionService;
import parkjinhee.projecttrektraverse.theme.entity.Theme;
import parkjinhee.projecttrektraverse.theme.service.ThemeService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final BoardService boardService;
    private final ThemeService themeService;


    public PostService(PostRepository postRepository, BoardService boardService, ThemeService themeService) {
        this.boardService = boardService;
        this.postRepository = postRepository;
        this.themeService = themeService;

    }

    public Page<Post> findPostsByBoardAndKeyword(Board board, String keyword, PageRequest pageRequest) {
        return keyword != null && !keyword.isEmpty() ? this.postRepository.findAllByBoardAndPostTitleContaining(board, keyword, pageRequest) : this.postRepository.findAllByBoardOrderByCreatedAtDesc(board, pageRequest);
    }
    public Page<Post> findPostsByThemeAndKeyword(Theme theme, String keyword, PageRequest pageRequest) {
        return keyword != null && !keyword.isEmpty() ? this.postRepository.findAllByThemeAndPostTitleContaining(theme, keyword, pageRequest) : this.postRepository.findAllByTheme(theme, pageRequest);
    }

    public List<Post> findPostsByBoardAndTheme(Long boardId, Long themeId) {
        return postRepository.findByBoardIdAndThemeId(boardId, themeId);
    }

    public Post findPost(Long postId) {
        return (Post)this.postRepository.findById(postId).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.POST_NOT_FOUND);
        });
    }

//    public Post createPost(Post post, Long boardId ) {
//        Board boardToCreate = this.boardService.findBoardById(boardId);
//        post.setBoard(boardToCreate);
//
//        Post savedPost = (Post)this.postRepository.save(post);
//        return savedPost;
//    }

    //전 작업
    public Post createPost(Post post, Board board, Theme theme, Region region) {
        post.setTheme(theme);
        post.setBoard(board);
        post.setRegion(region); // 추가된 코드
        Post savedPost = (Post)this.postRepository.save(post);
        return savedPost;
    }





    public Post updatePost(Post post, Long postId) {
        post.setId(postId);
        Post foundPost = (Post)this.postRepository.findById(post.getId()).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.POST_NOT_FOUND);
        });
        Optional.ofNullable(post.getPostTitle()).ifPresent((postTitle) ->{
            foundPost.setPostTitle(postTitle);
        });
        Optional.ofNullable(post.getPostContent()).ifPresent((postContent) ->{
            foundPost.setPostContent(postContent);
        });
        return (Post)this.postRepository.save(foundPost);
    }

    public void deletePost(Long id) {
        Post foundPost = (Post)this.postRepository.findById(id).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.POST_NOT_FOUND);
        });
        this.postRepository.delete(foundPost);
    }
}
