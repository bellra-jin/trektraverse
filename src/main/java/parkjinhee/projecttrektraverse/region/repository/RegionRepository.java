package parkjinhee.projecttrektraverse.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import parkjinhee.projecttrektraverse.region.entity.Region;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findRegionById(Long id);
    List<Region> findByGroupTableId(Long groupId);

}
