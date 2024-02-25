package parkjinhee.projecttrektraverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import parkjinhee.projecttrektraverse.board.repository.BoardRepository;
import parkjinhee.projecttrektraverse.post.repository.PostRepository;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectTrektraverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTrektraverseApplication.class, args);
	}


}
