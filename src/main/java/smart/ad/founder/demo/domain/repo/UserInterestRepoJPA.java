package smart.ad.founder.demo.domain.repo;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smart.ad.founder.demo.domain.model.entities.UserInterest;

public interface UserInterestRepoJPA extends JpaRepository<UserInterest, Long> {

    @Query("select p from UserInterest p where p.category=?1 and p.user.id=?2 order by p.id")
    List<UserInterest> findAllByCategory(String category, Long userId);

    @Query("select p from UserInterest p where p.region=?1 and p.user.id=?2 order by p.id")
    List<UserInterest> findAllByRegion(String region, Long userId);

    @Query("select p from UserInterest p where p.category=?1 and p.region=?2 and p.user.id=?3 order by p.id")
    List<UserInterest> findAllByCategoryAndRegion(String category, String region, Long userId);
}
