package parkjinhee.projecttrektraverse.region.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import parkjinhee.projecttrektraverse.groupTable.entity.GroupTable;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String region;

    @ManyToOne
    @JoinColumn(name = "groupTableId")
    @JsonBackReference
    private GroupTable groupTable;

    public Region() {}

    public Region(String region) {
        this.region = region;
    }

    @Builder
    public Region(String region, GroupTable groupTable) {
        this.region = region;
        this.groupTable = groupTable;
    }


}
