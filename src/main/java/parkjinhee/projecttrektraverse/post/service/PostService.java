package parkjinhee.projecttrektraverse.post.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.repository.BoardRepository;
import parkjinhee.projecttrektraverse.board.service.BoardService;
import parkjinhee.projecttrektraverse.global.exception.ExceptionCode;
import parkjinhee.projecttrektraverse.global.exception.ServiceLogicException;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostDto;
import parkjinhee.projecttrektraverse.post.entity.PostPwDto;
import parkjinhee.projecttrektraverse.post.entity.PostResponseDto;
import parkjinhee.projecttrektraverse.post.repository.PostRepository;
import parkjinhee.projecttrektraverse.region.entity.Region;
import parkjinhee.projecttrektraverse.region.repository.RegionRepository;
import parkjinhee.projecttrektraverse.region.service.RegionService;
import parkjinhee.projecttrektraverse.theme.entity.Theme;
import parkjinhee.projecttrektraverse.theme.repository.ThemeRepository;
import parkjinhee.projecttrektraverse.theme.service.ThemeService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final BoardService boardService;
    private final ThemeService themeService;

    private final BoardRepository boardRepository;

    private final ThemeRepository themeRepository;

    private final RegionService regionService;

    private final RegionRepository regionRepository;



    public PostService(PostRepository postRepository, BoardService boardService, ThemeService themeService, BoardRepository boardRepository, ThemeRepository themeRepository, RegionService regionService, RegionRepository regionRepository) {
        this.boardService = boardService;
        this.postRepository = postRepository;
        this.themeService = themeService;
        this.boardRepository = boardRepository;
        this.themeRepository = themeRepository;
        this.regionService = regionService;
        this.regionRepository= regionRepository;
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

    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    public List<Theme> findAllThemes() {
        return themeRepository.findAll();
    }

    public Long getBoardIdByPostId(Long postId) {
        // PostRepository에 정의한 메서드를 호출하여 게시판 ID를 가져옴
        return postRepository.findBoardIdByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
    }

    public Long getThemeIdByPostId(Long postId) {
        // PostRepository에 정의한 메서드를 호출하여 게시판 ID를 가져옴
        return postRepository.findThemeIdByPostId(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
    }

    public boolean checkPassword(PostPwDto postPwDto) {
        Post post = postRepository.findById(postPwDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postPwDto.getPostId()));
        return post.getPostPw().equals(postPwDto.getPostPw());
    }



    //전 작업
    public Post createPost(Post post, Long boardId, Long themeId, Long regionId) {

        Board boardToCreate = this.boardService.findBoardById(boardId);
        post.setBoard(boardToCreate);

        Theme themeToCreate = this.themeService.findThemeById(themeId);
        post.setTheme(themeToCreate);

        Region regionToCreate = this.regionService.findRegionById(regionId);
        post.setRegion(regionToCreate);


        Post savedPost = (Post)this.postRepository.save(post);
        return savedPost;
    }



    public Post updatePost(Post post, Long postId, Long boardId, Long themeId, Long regionId ) {
        post.setId(postId);

        Post foundPost = (Post)this.postRepository.findById(post.getId()).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.POST_NOT_FOUND);
        });

        Board boardToCreate = this.boardService.findBoardById(boardId);
        post.setBoard(boardToCreate);

        Theme themeToCreate = this.themeService.findThemeById(themeId);
        post.setTheme(themeToCreate);

        Region regionToCreate = this.regionService.findRegionById(regionId);
        post.setRegion(regionToCreate);

        Optional.ofNullable(post.getPostTitle()).ifPresent((postTitle) ->{
            foundPost.setPostTitle(postTitle);
        });
        Optional.ofNullable(post.getPostContent()).ifPresent((postContent) ->{
            foundPost.setPostContent(postContent);
        });
//        Optional.ofNullable(post.getPostPw()).ifPresent((postPw) ->{
//            foundPost.setPostPw(postPw);
//        });

        // Board 업데이트 로직
        Optional.ofNullable(post.getBoard()).ifPresent(board -> {
            Board foundBoard = boardRepository.findById(board.getId())
                    .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
            foundPost.setBoard(foundBoard);
        });

        // Region 업데이트 로직
        Optional.ofNullable(post.getRegion()).ifPresent(region -> {
            Region foundRegion = regionRepository.findById(region.getId())
                    .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
            foundPost.setRegion(foundRegion);
        });

        // Theme 업데이트 로직
        Optional.ofNullable(post.getTheme()).ifPresent(theme -> {
            // themeId가 null일 수 있다는 점을 감안하여 처리
            if (post.getTheme().getId() != null) {
                Theme foundTheme = themeRepository.findById(theme.getId())
                        .orElseThrow(() -> new ServiceLogicException(ExceptionCode.THEME_NOT_FOUND));
                foundPost.setTheme(foundTheme);
            } else {
                foundPost.setTheme(null); // themeId가 null이라면 theme을 null로 설정할 수도 있습니다.
            }
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
