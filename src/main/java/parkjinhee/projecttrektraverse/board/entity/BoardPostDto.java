package parkjinhee.projecttrektraverse.board.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardPostDto {
    private String boardTitle;

    public Board boardPostDtoToBoard(BoardPostDto boardPostDto) {
        if (boardPostDto == null) {
            return null;
        } else {
            Board.BoardBuilder board = Board.builder();
            board.boardTitle(boardPostDto.getBoardTitle());
            return board.build();
        }
    }

}
