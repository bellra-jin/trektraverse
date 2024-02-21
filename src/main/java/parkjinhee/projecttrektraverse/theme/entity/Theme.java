package parkjinhee.projecttrektraverse.theme.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String theme_title;
    private String theme_pw;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private LocalDateTime delete_date;

    @ColumnDefault("0")
    private int watch_count;
}
