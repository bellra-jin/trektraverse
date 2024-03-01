package parkjinhee.projecttrektraverse.post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.repository.BoardRepository;
import parkjinhee.projecttrektraverse.board.service.BoardService;
import parkjinhee.projecttrektraverse.comment.entity.Comment;
import parkjinhee.projecttrektraverse.comment.service.CommentService;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.entity.PostDto;
import parkjinhee.projecttrektraverse.post.entity.PostPwDto;
import parkjinhee.projecttrektraverse.post.entity.PostResponseDto;
import parkjinhee.projecttrektraverse.post.mapper.PostMapper;
import parkjinhee.projecttrektraverse.post.service.PostService;
import parkjinhee.projecttrektraverse.region.entity.Region;
import parkjinhee.projecttrektraverse.region.repository.RegionRepository;
import parkjinhee.projecttrektraverse.region.service.RegionService;
import parkjinhee.projecttrektraverse.theme.entity.Theme;
import parkjinhee.projecttrektraverse.theme.repository.ThemeRepository;
import parkjinhee.projecttrektraverse.theme.service.ThemeService;

import java.util.Collections;
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
    private final CommentService commentService;

    private final RegionService regionService;
    private final String string = "/create";

    public PostController(final PostService postService, final BoardService boardService, final ThemeService themeService,final PostMapper postMapper, final RegionService regionService, final CommentService commentService) {
        this.postService = postService;
        this.boardService = boardService;
        this.themeService = themeService;
        this.postMapper = postMapper;
        this.commentService = commentService;
        this.regionService = regionService;
    }

    @GetMapping({"/{postId}"})
    public String getPostDetail(@PathVariable("postId") Long postId, Model model) {
        Post post = this.postService.findPost(postId);
        model.addAttribute("post", post);
        List<Comment> comments = this.commentService.findCommentsByPostId(postId);
        model.addAttribute("comments", comments);
        return "post/post";
    }



    @GetMapping({"/create"})
    public String createPost(Model model, @RequestParam(value = "boardId", required = false) Long boardId,
                             @RequestParam(value = "themeId", required = false) Long themeId) {
        List<Board> boards = postService.findAllBoards();
        List<Theme> themes = postService.findAllThemes();

        // Model 객체에 속성으로 추가
        model.addAttribute("boards", boards);
        model.addAttribute("themes", themes);
        //boardId와 기본 themeId(0으로 설정)을 모델에 추가
        if (boardId != null) {
            model.addAttribute("boardId", boardId);
            model.addAttribute("themeId", 0);
        }
        return "post/createPost";
    }


    @GetMapping("/create/{boardId}")
    @ResponseBody
    public List<Region> getRegionsByBoard(@PathVariable("boardId") Long boardId) {
        return regionService.findByGroupId(boardId);
    }


    @PostMapping({"/create"})
    public String createPostPost(@ModelAttribute PostDto postDto, @RequestParam("boardId") Long boardId, @RequestParam("themeId") Long themeId, @RequestParam("regionId") Long regionId) {
        // PostDto를 Post 객체로 변환합니다.
        Post post = this.postMapper.postDTOToPost(postDto);

        // postDto, post 값이 같다면, post만 불러와도 됨.

        Post createdPost = this.postService.createPost(post, boardId, themeId, regionId);

        // Theme 값이 null이 아니면, 해당 Theme의 페이지로 리다이렉트합니다.
        // 그렇지 않으면, 해당 Board의 페이지로 리다이렉트합니다.
        if (themeId != null) {
            return "redirect:/themes/" + createdPost.getTheme().getId();
        } else {
            return "redirect:/boards/" + createdPost.getBoard().getId();
        }
    }

    @GetMapping("/edit/{boardId}")
    @ResponseBody
    public List<Region> getRegionsByBoard1(@PathVariable("boardId") Long boardId) {
        return regionService.findByGroupId(boardId);
    }


    // 비밀번호 확인 요청을 처리하는 메소드(수정)
    @RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkPassword(@RequestBody PostPwDto postPwDto) {
        return postService.checkPassword(postPwDto);
    }

    // 게시글 삭제를 위한 비밀번호 확인 요청을 처리하는 메소드

    @PostMapping("/deletePassword")
    public String deletePostWithPassword(@RequestParam("postId") Long postId,
                                         @RequestParam("postPw") String postPw,
                                         RedirectAttributes redirectAttributes) {
        // 삭제 후 리다이렉트할 기본 URL, 필요에 따라 조정 가능
        String redirectUrl = "/defaultPath";

        // 비밀번호 확인 로직 수행
        if (postService.checkPassword(new PostPwDto(postId, postPw))) {
            // 게시글 삭제 전에 boardId와 themeId 조회
            Long boardId = postService.getBoardIdByPostId(postId);
            Long themeId = postService.getThemeIdByPostId(postId);

            // 게시글 삭제 수행
            postService.deletePost(postId);

            // 삭제 성공 메시지를 flash attribute로 추가
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");

            if (themeId != null) {
                // post가 특정 테마에 속한 경우 해당 테마 페이지로 리다이렉트
                redirectUrl = "/themes/" + themeId;
            } else if (boardId != null) {
                // post가 특정 게시판에 속한 경우 해당 게시판 페이지로 리다이렉트
                redirectUrl = "/boards/" + boardId;
            }
        } else {
            // 비밀번호 오류 시
            Long boardId = postService.getBoardIdByPostId(postId); // 오류 시에도 boardId가 필요할 수 있음

            // 비밀번호 오류 메시지를 flash attribute로 추가
            redirectAttributes.addFlashAttribute("error", "비밀번호가 틀렸습니다.");
            // 비밀번호 오류 상황에서 사용자를 게시글 조회 페이지로 리다이렉트
            redirectUrl = "/board/post?postId=" + postId + "&boardId=" + boardId;
        }

        // 최종 결정된 URL로 리다이렉트
        return "redirect:" + redirectUrl;
    }



    // 실제 게시글 수정 페이지를여주는 메소드
    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable("postId") Long postId, Model model) {
        Post post = this.postService.findPost(postId);
        List<Board> boards = postService.findAllBoards();
        List<Theme> themes = postService.findAllThemes();

        model.addAttribute("post", post);
        model.addAttribute("boards", boards);
        model.addAttribute("themes", themes);

        return "post/editPost";
    }

    @PostMapping({"/{postId}/edit"})
    public String editPost(@PathVariable("postId") Long postId, @ModelAttribute PostDto postDto, RedirectAttributes redirectAttributes, @RequestParam("boardId") Long boardId, @RequestParam("themeId") Long themeId, @RequestParam("regionId") Long regionId) {
        Post post = this.postMapper.postDTOToPost(postDto);

        Post updatedPost = this.postService.updatePost(post, postId, boardId, themeId, regionId);

        // Redirect URL 초기화
        String redirectUrl = "/posts/{postId}";


        // 리디렉션시 필요한 속성들을 추가
        redirectAttributes.addAttribute("postId", updatedPost.getId());
        redirectAttributes.addAttribute("boardId", boardId);
        redirectAttributes.addAttribute("themeId", themeId);
        redirectAttributes.addAttribute("regionId", regionId);

        redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");

        // theme 또는 board 값에 따라 다른 리디렉션 URL 설정
        if (updatedPost.getTheme() != null) {
            redirectUrl = "/themes/" + updatedPost.getTheme().getId();
        } else if (updatedPost.getBoard() != null) {
            redirectUrl = "/boards/" + updatedPost.getBoard().getId();
        }

        return "redirect:" + redirectUrl;
    }


//    @DeleteMapping({"/{currentPost}"})
//    public String deletePost(@PathVariable("currentPost") Long postId, RedirectAttributes redirectAttributes) {
//        this.postService.deletePost(postId);
//        redirectAttributes.addFlashAttribute("message", "과목이 제거되었습니다.");
//        return "redirect:/posts";
//    }
//    @DeleteMapping({"/{postId}"})
//    public String deletePost(@PathVariable Long postId, RedirectAttributes redirectAttributes) {
//        this.postService.deletePost(postId);
//        redirectAttributes.addFlashAttribute("message", "과목이 제거되었습니다.");
//        return "redirect:/posts";
//    }






}
