package kimchisoup.matchingjob.repository;

import kimchisoup.matchingjob.entity.dao.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteUserRepository<T extends SiteUser> extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);

    boolean existsByEmail(String email);
}
