package parkjinhee.projecttrektraverse.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import parkjinhee.projecttrektraverse.comment.entity.Comment;
import parkjinhee.projecttrektraverse.comment.entity.CommentDto;
import parkjinhee.projecttrektraverse.comment.mapper.CommentMapper;
import parkjinhee.projecttrektraverse.comment.service.CommentService;

@Controller
@RequestMapping({"/comments"})
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    public CommentController(final CommentService commentService, final CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping
    public String createComment(@ModelAttribute CommentDto commentDto, @RequestParam("postId") Long postId, RedirectAttributes redirectAttributes) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        commentService.createComment(postId, comment);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @PostMapping({"/{commentId}/edit"})
    public String updateComment(@PathVariable("commentId") Long commentId, @ModelAttribute CommentDto commentDto, RedirectAttributes redirectAttributes) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        Comment updatedComment = commentService.updateComment(commentId, comment);


        redirectAttributes.addAttribute("postId", updatedComment.getPost().getId());
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping({"/{commentId}"})
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/posts";
    }
}
