package parkjinhee.projecttrektraverse.region.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import parkjinhee.projecttrektraverse.region.entity.Region;
import parkjinhee.projecttrektraverse.region.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Transactional(readOnly = true)
    public List<Region> findByGroupId(Long groupId) {
        return regionRepository.findByGroupTableId(groupId);
    }
}

