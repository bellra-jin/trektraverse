package parkjinhee.projecttrektraverse.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.entity.BoardPostDto;
import parkjinhee.projecttrektraverse.board.mapper.BoardMapper;
import parkjinhee.projecttrektraverse.board.service.BoardService;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.service.PostService;

import java.util.List;

@Controller
@RequestMapping({"/boards"})
public class BoardController {
    private final BoardService boardService;
    private final PostService postService;
    private final BoardMapper boardMapper;

    public BoardController(BoardService boardService, PostService postService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.postService = postService;
        this.boardMapper = boardMapper;
    }

    @GetMapping
    public String getBoards(Model model) {
        List<Board> boards = this.boardService.findBoards();
        model.addAttribute("boards", boards);
        return "board/boards";
    }

    @GetMapping({"/{boardId}"})
    public String getBoard(@PathVariable Long boardId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String keyword, Model model) {
        Board board = this.boardService.findBoardById(boardId);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage = this.postService.findPostsByBoardAndKeyword(board, keyword, pageRequest);
        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);
        return "board/board";
    }




}
