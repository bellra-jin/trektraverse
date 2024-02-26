package parkjinhee.projecttrektraverse.board.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.entity.BoardPostDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-25T22:43:03+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public Board boardPostDtoToBoard(BoardPostDto boardPostDto) {
        if ( boardPostDto == null ) {
            return null;
        }

        Board.BoardBuilder board = Board.builder();

        board.boardTitle( boardPostDto.getBoardTitle() );

        return board.build();
    }
}
