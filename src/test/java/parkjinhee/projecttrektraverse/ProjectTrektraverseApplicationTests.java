package parkjinhee.projecttrektraverse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootTest
class ProjectTrektraverseApplicationTests {

	@Test
	void contextLoads() {
	}

}
