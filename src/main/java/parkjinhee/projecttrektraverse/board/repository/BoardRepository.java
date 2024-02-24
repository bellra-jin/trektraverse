package parkjinhee.projecttrektraverse.board.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.groupTable.entity.GroupTable;
import parkjinhee.projecttrektraverse.region.entity.Region;

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

    private final RowMapper<Board> boardRowMapper = (rs, rowNum) -> {
        Board board = new Board();
        board.setId(rs.getLong("board_id"));
        board.setBoardTitle(rs.getString("board_title"));

        GroupTable groupTable = new GroupTable();
        groupTable.setId(rs.getLong("group_id"));
        groupTable.setGroupTitle(rs.getString("group_title"));
        groupTable.setBoard(board);

        Region region = new Region();
        region.setId(rs.getLong("region_id"));
        region.setRegion(rs.getString("region_region"));
        region.setGroupTable(groupTable);

        return board;
    };

    public Optional<Board> findByIdWithGroupsAndRegions(Long boardId) {
        String sql = "SELECT b.id as board_id, b.name as board_title, " +
                "g.id as group_id, g.name as group_title, " +
                "r.id as region_id, r.name as region " +
                "FROM Board b " +
                "JOIN Group g ON b.id = g.board_id " +
                "JOIN Region r ON g.id = r.group_id " +
                "WHERE b.id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, boardRowMapper, boardId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

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
