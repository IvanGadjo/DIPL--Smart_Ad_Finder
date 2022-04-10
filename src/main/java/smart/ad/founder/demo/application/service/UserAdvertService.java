package smart.ad.founder.demo.application.service;

import smart.ad.founder.demo.domain.model.entities.UserAdvert;
import smart.ad.founder.demo.domain.model.entities.UserInterest;

import java.util.List;

public interface UserAdvertService {

    List<UserAdvert> findAllUserAdverts();

    List<UserAdvert> findAllUserAdvertsOfUser(Long userId);

    UserAdvert findUserAdvertById(Long id);

    UserAdvert editUserAdvert(UserAdvert newUserAdvert, Long userId) throws Exception;

    UserAdvert addNewUserAdvert(UserAdvert userAdvert, Long userId) throws Exception;

    void deleteUserAdvertById(Long id);

}

