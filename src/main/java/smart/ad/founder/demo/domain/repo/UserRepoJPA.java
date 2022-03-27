package smart.ad.founder.demo.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import smart.ad.founder.demo.domain.model.entities.User;

public interface UserRepoJPA extends JpaRepository<User, Long> {
}
