package smart.ad.founder.demo.application.repo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.ad.founder.demo.domain.model.entities.UserAdvert;
import smart.ad.founder.demo.domain.repo.UserAdvertRepoJPA;

import java.util.List;

@Service
@Transactional
public class UserAdvertsRepo {

    UserAdvertRepoJPA userAdvertRepoJPA;

    public UserAdvertsRepo(UserAdvertRepoJPA userAdvertRepoJPA) {
        this.userAdvertRepoJPA = userAdvertRepoJPA;
    }

    public List<UserAdvert> findAll() {
        return userAdvertRepoJPA.findAll();
    }

    public UserAdvert findById(Long id) {
        return userAdvertRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public UserAdvert editUserAdvert(UserAdvert newUserAdvert) {
        UserAdvert old = userAdvertRepoJPA.findById(newUserAdvert.getId()).orElseThrow(RuntimeException::new);

        old.setCategory(newUserAdvert.getCategory());
        old.setUser(newUserAdvert.getUser());
        old.setRegion(newUserAdvert.getRegion());
        old.setDescription(newUserAdvert.getDescription());
        old.setImage(newUserAdvert.getImage());
        old.setIsActive(newUserAdvert.getIsActive());
        old.setPrice(newUserAdvert.getPrice());
        old.setTitle(newUserAdvert.getTitle());
        return userAdvertRepoJPA.save(old);
    }

    public UserAdvert saveNewUserAdvert(UserAdvert userAdvert) {
        return userAdvertRepoJPA.save(userAdvert);
    }

    public void deleteById(Long id) {
        userAdvertRepoJPA.deleteById(id);
    }
}
