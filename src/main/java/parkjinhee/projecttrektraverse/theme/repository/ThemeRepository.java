package parkjinhee.projecttrektraverse.theme.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.theme.entity.Theme;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ThemeRepository {

//    private DataSoure dataSoure;

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ThemeRepository(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}

    private RowMapper<Theme> themeRowMapper() {
        return (resultSet, rowNum) -> {
            return Theme.builder().id(resultSet.getLong("id")).themeTitle(resultSet.getString("theme_title")).themePw(resultSet.getString("theme_pw")).createdAt(resultSet.getTimestamp("created_at").toLocalDateTime()).build();
        };
    }

    public List<Theme> findAll() {
        String sql = "SELECT * FROM theme";
        return this.jdbcTemplate.query(sql, this.themeRowMapper());
    }

    public Optional<Theme> findById(Long id) {
        try {
            String sql = "SELECT * FROM theme WHERE id = ?";
            Theme theme = (Theme)this.jdbcTemplate.queryForObject(sql, this.themeRowMapper(), new Object[]{id});
            return Optional.ofNullable(theme);
        } catch (EmptyResultDataAccessException var4) {
            return Optional.empty();
        }
    }
    public Theme create(Theme theme) {
        String insertSql = "INSERT INTO theme (theme_title, theme_pw, created_at) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime createdAt = LocalDateTime.now();
        this.jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{"id"});
            ps.setString(1, theme.getThemeTitle());
            ps.setString(2, theme.getThemePw());
            ps.setTimestamp(3, Timestamp.valueOf(createdAt));
            return ps;
        }, keyHolder);
        Number key = keyHolder.getKey();
        return key == null ? theme : theme.toBuilder().id(key.longValue()).themeTitle(theme.getThemeTitle()).themePw(theme.getThemePw()).createdAt(createdAt).build();
    }

    public Theme update(Theme theme) {
        String updateSql = "UPDATE theme SET theme_title = ?, theme_pw = ? WHERE id = ?";
        this.jdbcTemplate.update(updateSql, new Object[]{theme.getThemeTitle(), theme.getThemePw(), theme.getId()});
        return theme;
    }

    public void delete(Theme theme) {
        String sql = "DELETE FROM theme WHERE id = ?";
        this.jdbcTemplate.update(sql, new Object[]{theme.getId()});
    }

}
