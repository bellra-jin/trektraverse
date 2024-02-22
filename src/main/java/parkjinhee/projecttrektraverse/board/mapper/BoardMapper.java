package parkjinhee.projecttrektraverse.board.mapper;


import parkjinhee.projecttrektraverse.board.entity.Board;

import parkjinhee.projecttrektraverse.board.entity.BoardPostDto;

import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface BoardMapper {
  Board boardPostDtoToBoard(BoardPostDto boardPostDto);
}