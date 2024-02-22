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

import java.util.Optional;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final BoardService boardService;

    public PostService(PostRepository postRepository, BoardService boardService) {
        this.boardService = boardService;
        this.postRepository = postRepository;
    }

    public Page<Post> findPostsByBoardAndKeyword(Board board, String keyword, PageRequest pageRequest) {
        return keyword != null && !keyword.isEmpty() ? this.postRepository.findAllByBoardAndPostTitleContaining(board, keyword, pageRequest) : this.postRepository.findAllByBoardOrderByCreatedAtDesc(board, pageRequest);
    }

    public Post findPost(Long postId) {
        return (Post)this.postRepository.findById(postId).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.POST_NOT_FOUND);
        });
    }

    public Post createPost(Post post, Long boardId) {
        Board boardToCreate = this.boardService.findBoardById(boardId);
        post.setBoard(boardToCreate);
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
