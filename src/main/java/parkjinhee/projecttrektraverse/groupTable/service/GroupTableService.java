package parkjinhee.projecttrektraverse.groupTable.service;

import org.springframework.transaction.annotation.Transactional;
import parkjinhee.projecttrektraverse.groupTable.entity.GroupTable;
import parkjinhee.projecttrektraverse.groupTable.repository.GroupTableRepository;

import java.util.List;

public class GroupTableService {
    private final GroupTableRepository groupTableRepository;

    public GroupTableService(GroupTableRepository groupTableRepository) {
        this.groupTableRepository = groupTableRepository;
    }

    @Transactional(readOnly = true)
    public List<GroupTable> findByBoardId(Long boardId) {
        return groupTableRepository.findByBoardId(boardId);
    }
}
