package smart.ad.founder.demo.domain.repo;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smart.ad.founder.demo.domain.model.entities.UserInterest;

public interface UserInterestRepoJPA extends JpaRepository<UserInterest, Long> {

    @Query("select p from UserInterest p where p.category=?1 order by p.id")
    List<UserInterest> findAllByCategory(String category);

    @Query("select p from UserInterest p where p.region=?1 order by p.id")
    List<UserInterest> findAllByRegion(String region);

    @Query("select p from UserInterest p where p.category=?1 and p.region=?2 order by p.id")
    List<UserInterest> findAllByCategoryAndRegion(String category, String region);
}
