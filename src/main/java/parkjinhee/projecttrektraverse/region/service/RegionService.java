package parkjinhee.projecttrektraverse.region.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import parkjinhee.projecttrektraverse.global.exception.ExceptionCode;
import parkjinhee.projecttrektraverse.global.exception.ServiceLogicException;
import parkjinhee.projecttrektraverse.region.entity.Region;
import parkjinhee.projecttrektraverse.region.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region createRegion(Region region) {return  this.regionRepository.save(region);}

//    public Region findRegionById(Long id){return regionRepository.findRegionById(id);}
    public Region findRegionById(Long id) {
        return (Region) this.regionRepository.findById(id).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND);
        });
    }

    @Transactional(readOnly = true)
    public List<Region> findByGroupId(Long groupId) {
        return regionRepository.findByGroupTableId(groupId);
    }
}

