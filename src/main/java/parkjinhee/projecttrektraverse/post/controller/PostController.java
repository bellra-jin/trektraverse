package parkjinhee.projecttrektraverse.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.repository.BoardRepository;
import parkjinhee.projecttrektraverse.board.service.BoardService;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostDto;
import parkjinhee.projecttrektraverse.post.mapper.PostMapper;
import parkjinhee.projecttrektraverse.post.service.PostService;
import parkjinhee.projecttrektraverse.region.entity.Region;
import parkjinhee.projecttrektraverse.region.repository.RegionRepository;
import parkjinhee.projecttrektraverse.region.service.RegionService;
import parkjinhee.projecttrektraverse.theme.entity.Theme;
import parkjinhee.projecttrektraverse.theme.repository.ThemeRepository;
import parkjinhee.projecttrektraverse.theme.service.ThemeService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping({"/posts"})
public class PostController {

    private final PostService postService;
    private final BoardService boardService;
    private final ThemeService themeService;
    //private final ImgFileService imgFileService;
    //private final MapService mapService;
    private final PostMapper postMapper;
    //private final CommentService commentService;


    private final ThemeRepository themeRepository;
    private final BoardRepository boardRepository;

    private final RegionRepository regionRepository;
    private final RegionService regionService;
    private final String string = "/create";

    public PostController(final PostService postService, final BoardService boardService, final ThemeService themeService,final PostMapper postMapper, final ThemeRepository themeRepository, final BoardRepository boardRepository, final RegionRepository regionRepository, final RegionService regionService){ //, final CommentService commentService) {
        this.postService = postService;
        this.boardService = boardService;
        this.themeService = themeService;
        this.postMapper = postMapper;
        this.regionRepository = regionRepository;
        //this.commentService = commentService;
        this.themeRepository = themeRepository;
        this.boardRepository = boardRepository;
        this.regionService = regionService;
    }

    @GetMapping({"/{postId}"})
    public String getPostDetail(@PathVariable("postId") Long postId, Model model) {
        Post post = this.postService.findPost(postId);
        model.addAttribute("post", post);
//        List<Comment> comments = this.commentService.findCommentsByPostId(postId);
//        model.addAttribute("comments", comments);
        return "post/post";
    }

    @GetMapping({"/create"})
    public String createPost(Model model, @RequestParam(value = "boardId", required = false) Long boardId,
                             @RequestParam(value = "themeId", required = false) Long themeId) {
        List<Board> boards = boardRepository.findAll();
        List<Theme> themes = themeRepository.findAll();
        model.addAttribute("boards", boards);
        model.addAttribute("themes", themes);
        if (boardId != null) {
            model.addAttribute("boardId", boardId);
            model.addAttribute("themeId", 0);
        } else if (themeId != null) {
            model.addAttribute("themeId", themeId);
            model.addAttribute("boardId", 0);
        }
        return "post/createPost";
    }


    @GetMapping("/create/{boardId}")
    @ResponseBody
    public List<Region> getRegionsByBoard(@PathVariable("boardId") Long boardId) {
        return regionService.findByGroupId(boardId);
    }




    @PostMapping({"/create"})
    public String createPostPost(@ModelAttribute PostDto postDto) {
        // PostDto에서 ID 값을 가져옵니다.
        Long boardId = postDto.getBoardId();
        Long themeId = postDto.getThemeId();
        Long regionId = postDto.getRegionId();

        // 각 ID를 사용해 Board, Theme, Region 객체를 찾습니다.
        // 이 때, ID 값이 유효하지 않으면 예외를 발생시킵니다.
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("No Board found with ID: " + boardId));
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new NoSuchElementException("No Region found with ID: " + regionId));
        Theme theme = null;
        if (themeId != null) {
            theme = themeRepository.findById(themeId).orElseThrow(() -> new NoSuchElementException("No Theme found with ID: " + themeId));
        }

        // PostDto를 Post 객체로 변환합니다.
        Post post = this.postMapper.postDTOToPost(postDto);

        // Post 객체에 Board, Theme, Region 객체를 설정합니다.
        post.setBoard(board);
        post.setRegion(region);
        post.setTheme(theme);

        // Post 객체를 저장하고, 저장된 객체를 반환받습니다.
        Post createdPost = this.postService.createPost(post, board, theme, region);

        // Theme 값이 null이 아니면, 해당 Theme의 페이지로 리다이렉트합니다.
        // 그렇지 않으면, 해당 Board의 페이지로 리다이렉트합니다.
        if (theme != null) {
            return "redirect:/themes/" + theme.getId();
        } else {
            return "redirect:/boards/" + board.getId();
        }
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






}
