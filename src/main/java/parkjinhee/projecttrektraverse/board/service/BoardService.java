package parkjinhee.projecttrektraverse.board.service;

import org.springframework.stereotype.Service;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.repository.BoardRepository;
import parkjinhee.projecttrektraverse.global.exception.ExceptionCode;
import parkjinhee.projecttrektraverse.global.exception.ServiceLogicException;

import java.util.List;

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


}
