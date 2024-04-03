package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.common.RegionType;
import kimchisoup.matchingjob.entity.dao.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<List<Region>> findRegionsByRegionType(RegionType regionType);
}
