package parkjinhee.projecttrektraverse.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {
    @CreatedDate
    @Column(
            name = "create_date",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(
            name = "updated_date",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime updatedAt;

    public BaseEntity() {
    }

}
