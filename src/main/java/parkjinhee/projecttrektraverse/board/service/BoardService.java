package parkjinhee.projecttrektraverse.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.repository.BoardRepository;
import parkjinhee.projecttrektraverse.global.exception.ExceptionCode;
import parkjinhee.projecttrektraverse.global.exception.ServiceLogicException;
import parkjinhee.projecttrektraverse.theme.entity.Theme;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    private Board foundBoard;
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public List<Board> findBoards() {
        return this.boardRepository.findAll();
    }

    public Board findBoardById(Long id) {
        return (Board)this.boardRepository.findById(id).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND);
        });
    }

    public Board createBoard(Board board){return this.boardRepository.create(board);}

    @Transactional(readOnly = true)
    public Board findBoardWithGroups(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
    }




}
