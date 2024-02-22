package parkjinhee.projecttrektraverse.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import parkjinhee.projecttrektraverse.board.service.BoardService;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostDto;
import parkjinhee.projecttrektraverse.post.mapper.PostMapper;
import parkjinhee.projecttrektraverse.post.service.PostService;

@Controller
@RequestMapping({"/posts"})
public class PostController {

    private final PostService postService;
    private final BoardService boardService;
    //private final ThemeService themeService;
    //private final ImgFileService imgFileService;
    //private final MapService mapService;
    private final PostMapper postMapper;
    //private final CommentService commentService;


//    @GetMapping({"/{postId}"})
//    public String getPostDetail(@PathVariable Long postId, Model model){
//        Post post = this.postService.findPost(postId);
//        model.addAttribute("post", post);
//        List<Comment> comments = this.commentService.findCommentsByPostId(postId);
//        model.addAttribute("comments", comments);
//        return "post/post";
//    }


    @GetMapping({"/create"})
    public String createPost(@RequestParam Long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        return "post/createPost";
    }

    @PostMapping({"/create"})
    public String createPostPost(@ModelAttribute PostDto postDto, @RequestParam Long boardId) {
        Post post = this.postMapper.postDTOToPost(postDto);
        Post createdPost = this.postService.createPost(post, boardId);
        return "redirect:/boards/" + createdPost.getBoard().getId();
    }

    @GetMapping({"/{postId}/edit"})
    public String editPost(@PathVariable Long postId, Model model) {
        Post post = this.postService.findPost(postId);
        model.addAttribute("post", post);
        return "post/editPost";
    }

    @PostMapping({"/{postId}/edit"})
    public String editPost(@PathVariable Long postId, @ModelAttribute PostDto postDto, RedirectAttributes redirectAttributes) {
        Post post = this.postMapper.postDTOToPost(postDto);
        Post updatedPost = this.postService.updatePost(post, postId);
        redirectAttributes.addAttribute("postId", updatedPost.getId());
        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
        return "redirect:/posts/{postId}";
    }

    @DeleteMapping({"/{postId}"})
    public String deletePost(@PathVariable Long postId, RedirectAttributes redirectAttributes) {
        this.postService.deletePost(postId);
        redirectAttributes.addFlashAttribute("message", "과목이 제거되었습니다.");
        return "redirect:/posts";
    }

    public PostController(final PostService postService, final BoardService boardService, final PostMapper postMapper){ //, final CommentService commentService) {
        this.postService = postService;
        this.boardService = boardService;
        this.postMapper = postMapper;
        //this.commentService = commentService;
    }





}
