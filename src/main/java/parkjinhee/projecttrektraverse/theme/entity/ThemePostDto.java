package parkjinhee.projecttrektraverse.theme.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import parkjinhee.projecttrektraverse.board.entity.Board;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
public class ThemePostDto {
    private String themeTitle;
    private String themePw;


//    public static ThemePostDtoBuilder builder() {
//        return new ThemePostDtoBuilder();
//    }
//
//    public String getThemeTitle() {
//        return this.themeTitle;
//    }
//
//    public String getThemePw() {
//        return this.themePw;
//    }


    @ConstructorProperties({"themeTitle", "themePw"})
    public ThemePostDto(String themeTitle, String themePw) {
        this.themeTitle = themeTitle;
        this.themePw = themePw;
    }


//    public static class ThemePostDtoBuilder {
//        private String themeTitle;
//        private String themePw;
//
//        ThemePostDtoBuilder() {
//        }
//
//        public ThemePostDtoBuilder themTitle(final String themTitle) {
//            this.themeTitle = themeTitle;
//            return this;
//        }
//
//        public ThemePostDtoBuilder themePw(final String themePw) {
//            this.themePw = themePw;
//            return this;
//        }
//
//        public ThemePostDto build() {
//            return new ThemePostDto(this.themeTitle, this.themePw);
//        }
//
//        public String toString() {
//            return "BoardPostDto.BoardPostDtoBuilder(title=" + this.themeTitle + ", password=" + this.themePw + ")";
//        }
//    }


}


