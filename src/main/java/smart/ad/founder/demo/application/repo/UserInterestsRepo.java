package smart.ad.founder.demo.application.repo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.ad.founder.demo.domain.model.entities.UserInterest;
import smart.ad.founder.demo.domain.repo.UserInterestRepoJPA;

import java.util.List;

@Service
@Transactional

public class UserInterestsRepo {

    UserInterestRepoJPA userInterestRepoJPA;

    public UserInterestsRepo(UserInterestRepoJPA userInterestRepoJPA) {
        this.userInterestRepoJPA = userInterestRepoJPA;
    }

    public List<UserInterest> findAll() {
        return userInterestRepoJPA.findAll();
    }

    public UserInterest findById(Long id) {
        return userInterestRepoJPA.findById(id).orElseThrow(RuntimeException::new);
    }

    public UserInterest editUserInterest(UserInterest newUserInterest) {
        UserInterest old = userInterestRepoJPA.findById(newUserInterest.getId()).orElseThrow(RuntimeException::new);
        old.setKeywords(newUserInterest.getKeywords());
        old.setTimeValObject(newUserInterest.getTimeValObject());
        old.setCategory(newUserInterest.getCategory());
        old.setRegion(newUserInterest.getRegion());
        old.setCategory(newUserInterest.getCategory());
        old.setUser(newUserInterest.getUser());
        old.setFoundAdverts(newUserInterest.getFoundAdverts());
        old.setActive(newUserInterest.isActive());
        return userInterestRepoJPA.save(old);
    }

    public UserInterest saveNewUserInterest(UserInterest userInterest) {
        return userInterestRepoJPA.save(userInterest);
    }

    public void deleteById(Long id) {
        userInterestRepoJPA.deleteById(id);
    }

    public List<UserInterest> findAllByCategory(String category) {
        return userInterestRepoJPA.findAllByCategory(category);
    }

    public List<UserInterest> findAllByRegion(String region) {
        return userInterestRepoJPA.findAllByRegion(region);
    }

    public List<UserInterest> findAllByCategoryAndRegion(String category, String region) {
        return userInterestRepoJPA.findAllByCategoryAndRegion(category, region);
    }
}
