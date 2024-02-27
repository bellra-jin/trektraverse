package parkjinhee.projecttrektraverse.comment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parkjinhee.projecttrektraverse.comment.entity.Comment;
import parkjinhee.projecttrektraverse.comment.repository.CommentRepository;
import parkjinhee.projecttrektraverse.global.exception.ExceptionCode;
import parkjinhee.projecttrektraverse.global.exception.ServiceLogicException;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> findComments() {
        return commentRepository.findAll();
    }


    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND);
        });
    }

    public List<Comment> findCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.POST_NOT_FOUND);
        });
        log.info(post.getPostTitle());
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, Comment comment) {
        comment.setId(commentId);

        Comment foundComment = commentRepository.findById(comment.getId()).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND);
        });

        Optional.ofNullable(comment.getCommentContent()).ifPresent((commentContent) -> {
            foundComment.setCommentContent(commentContent);
        });

        Optional.ofNullable(comment.getCommentWriter()).ifPresent((commentWriter) -> {
            foundComment.setCommentPw(commentWriter);
        });

        Optional.ofNullable(comment.getCommentPw()).ifPresent((commentPw) -> {
            foundComment.setCommentPw(commentPw);
        });

        return commentRepository.save(foundComment);
    }

    public void deleteComment(Long commentId) {
        Comment foundComment = commentRepository.findById(commentId).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.COMMENT_NOT_FOUND);
        });
        commentRepository.delete(foundComment);
    }
}
