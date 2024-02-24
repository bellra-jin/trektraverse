package parkjinhee.projecttrektraverse.groupTable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import parkjinhee.projecttrektraverse.groupTable.entity.GroupTable;

import java.util.List;


@Repository
public interface GroupTableRepository extends JpaRepository<GroupTable, Long> {
    List<GroupTable> findByBoardId(Long boardId);
}
