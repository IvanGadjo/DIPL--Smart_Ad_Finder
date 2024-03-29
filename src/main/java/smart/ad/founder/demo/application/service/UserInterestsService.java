package smart.ad.founder.demo.application.service;

import smart.ad.founder.demo.domain.model.entities.UserInterest;

import java.io.IOException;
import java.util.List;

public interface UserInterestsService {

    List<UserInterest> findAllUserInterests();

    List<UserInterest> findAllUserInterestsOfUser(Long userId);

    UserInterest findUserInterestById(Long id);

    UserInterest editUserInterest(UserInterest newUserInterest, Long userId) throws Exception;

    UserInterest addNewUserInterest(UserInterest userInterest, Long userId) throws Exception;

    void deleteUserInterestById(Long id);



    List<UserInterest> findAllByCategory(String category, Long userId);

    List<UserInterest> findAllByRegion(String region, Long userId);

    List<UserInterest> findAllByCategoryAndRegion(String category, String region, Long userId);
}
