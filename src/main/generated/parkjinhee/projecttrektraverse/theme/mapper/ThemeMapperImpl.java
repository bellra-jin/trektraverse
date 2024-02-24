package parkjinhee.projecttrektraverse.theme.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import parkjinhee.projecttrektraverse.theme.entity.Theme;
import parkjinhee.projecttrektraverse.theme.entity.ThemePostDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-25T02:22:22+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ThemeMapperImpl implements ThemeMapper {

    @Override
    public Theme themePostDtoToTheme(ThemePostDto themePostDto) {
        if ( themePostDto == null ) {
            return null;
        }

        Theme.ThemeBuilder theme = Theme.builder();

        theme.themeTitle( themePostDto.getThemeTitle() );
        theme.themePw( themePostDto.getThemePw() );

        return theme.build();
    }
}
