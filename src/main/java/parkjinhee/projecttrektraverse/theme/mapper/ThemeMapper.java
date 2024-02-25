package parkjinhee.projecttrektraverse.theme.mapper;


import org.mapstruct.Mapper;
import parkjinhee.projecttrektraverse.board.entity.BoardPostDto;
import parkjinhee.projecttrektraverse.theme.entity.Theme;
import parkjinhee.projecttrektraverse.theme.entity.ThemePostDto;

@Mapper(
        componentModel = "spring"
)
public interface ThemeMapper {
  Theme themePostDtoToTheme(ThemePostDto themePostDto);
}