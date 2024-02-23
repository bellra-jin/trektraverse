package parkjinhee.projecttrektraverse.board.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import parkjinhee.projecttrektraverse.board.entity.Board;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}

    private RowMapper<Board> boardRowMapper() {
        return (resultSet, rowNum) -> {
            return Board.builder().id(resultSet.getLong("id")).boardTitle(resultSet.getString("board_title")).createdAt(resultSet.getTimestamp("created_at").toLocalDateTime()).build();
        };
    }

    public List<Board> findAll() {
        String sql = "SELECT * FROM board";
        return this.jdbcTemplate.query(sql, this.boardRowMapper());
    }

    public Optional<Board> findById(Long id) {
        try {
            String sql = "SELECT * FROM board WHERE id = ?";
            Board board = (Board)this.jdbcTemplate.queryForObject(sql, this.boardRowMapper(), new Object[]{id});
            return Optional.ofNullable(board);
        } catch (EmptyResultDataAccessException var4) {
            return Optional.empty();
        }
    }
    public Board create(Board board) {
        String insertSql = "INSERT INTO board (board_title, created_at) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime createdAt = LocalDateTime.now();
        this.jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[]{"id"});
            ps.setString(1, board.getBoardTitle());
            ps.setTimestamp(2, Timestamp.valueOf(createdAt));
            return ps;
        }, keyHolder);
        Number key = keyHolder.getKey();
        return key == null ? board : board.builder().id(key.longValue()).boardTitle(board.getBoardTitle()).createdAt(createdAt).build();
    }

}
