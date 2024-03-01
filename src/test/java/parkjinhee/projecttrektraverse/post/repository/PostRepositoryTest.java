package parkjinhee.projecttrektraverse.post.repository;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.board.repository.BoardRepository;
import parkjinhee.projecttrektraverse.post.entity.Post;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class PostRepositoryTest {

    @Autowired private PostRepository postRepository;
    @Autowired private BoardRepository boardRepository;

//    @Test
//    void save(){
//        Board board = Board.builder()
//                .boardTitle("게시판")
//                .build();
//
//        Board createdBoard = boardRepository.create(board);
//        Post post = new Post("게시물","게시물 내용","박진희","1234",createdBoard,"서울");
//
//        Post createdPost = postRepository.save(post);
//
//        Post findPost = postRepository.findById(1L).orElse(null);
//
//        Assertions.assertThat(createdPost.getId()).isEqualTo(findPost.getId());
//
//    }

//    @Test
//    void make(){
//        Board board = Board.builder()
//                .boardTitle("테마")
//                .build();
//        boardRepository.create(board);
//    }
//    @Test
//    void printTest(){
//        System.out.println("");
//    }

}