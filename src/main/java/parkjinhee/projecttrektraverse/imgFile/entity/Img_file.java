package parkjinhee.projecttrektraverse.imgFile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Img_file {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String file_name;
    private String file_url;
    private long post_id;
    private long comment_id;
}
