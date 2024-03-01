package parkjinhee.projecttrektraverse.theme.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import parkjinhee.projecttrektraverse.theme.entity.Theme;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("board CRUD 테스트(JdbcTemplate)")
class ThemeRepositoryTest {

    @Autowired
    ThemeRepository themeRepository;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

//    @Test
//    void update() {
//        //given
//        Theme theme1 = Theme.builder()
//                .themePw("title")
//                .themeTitle("test11")
//                .build();
//        theme1 = themeRepository.create(theme1);
//
//        //when
//        theme1.setThemeTitle("update");
//        themeRepository.update(theme1);
//        Theme actual = themeRepository.findById(theme1.getId()).get();
//
//        //then
//        Assertions.assertEquals(theme1.toString(), actual.toString());
//    }

    @Test
    void delete() {
    }
}