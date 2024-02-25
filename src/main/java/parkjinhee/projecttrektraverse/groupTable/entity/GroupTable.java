package parkjinhee.projecttrektraverse.groupTable.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;
import parkjinhee.projecttrektraverse.board.entity.Board;
import parkjinhee.projecttrektraverse.region.entity.Region;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GroupTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    @JsonBackReference
    private Board board;

    @OneToMany(mappedBy = "groupTable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Region> regions = new ArrayList<>();

}
