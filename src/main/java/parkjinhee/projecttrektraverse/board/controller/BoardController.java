package parkjinhee.projecttrektraverse.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.entity.BoardPostDto;
import parkjinhee.projecttrektraverse.board.mapper.BoardMapper;
import parkjinhee.projecttrektraverse.board.repository.BoardRepository;
import parkjinhee.projecttrektraverse.board.service.BoardService;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.service.PostService;
import parkjinhee.projecttrektraverse.region.entity.Region;
import parkjinhee.projecttrektraverse.region.service.RegionService;

import java.util.List;

@Controller
@RequestMapping({"/boards"})
public class BoardController {
    private final BoardService boardService;
    private final PostService postService;
    private final BoardMapper boardMapper;

    private final BoardRepository boardRepository;



    public BoardController(BoardService boardService, PostService postService, BoardMapper boardMapper, BoardRepository boardRepository) {
        this.boardService = boardService;
        this.postService = postService;
        this.boardMapper = boardMapper;
        this.boardRepository = boardRepository;

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

    @Transactional(readOnly = true)
    public Board findBoardWithGroups(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
    }







}
