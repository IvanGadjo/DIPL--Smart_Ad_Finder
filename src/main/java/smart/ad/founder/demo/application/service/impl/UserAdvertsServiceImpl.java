package smart.ad.founder.demo.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.ad.founder.demo.application.repo.UserAdvertsRepo;
import smart.ad.founder.demo.application.repo.UsersRepo;
import smart.ad.founder.demo.application.service.UserAdvertService;
import smart.ad.founder.demo.domain.model.entities.User;
import smart.ad.founder.demo.domain.model.entities.UserAdvert;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserAdvertsServiceImpl implements UserAdvertService {

    UserAdvertsRepo userAdvertsRepo;
    UsersRepo usersRepo;

    public UserAdvertsServiceImpl(UserAdvertsRepo userAdvertsRepo, UsersRepo usersRepo) {
        this.userAdvertsRepo = userAdvertsRepo;
        this.usersRepo = usersRepo;
    }

    @Override
    public List<UserAdvert> findAllUserAdverts() {
        return userAdvertsRepo.findAll();
    }

    @Override
    public List<UserAdvert> findAllUserAdvertsOfUser(Long userId) {
        List<UserAdvert> userAdvertsOfUser = userAdvertsRepo.findAll().stream().filter(usAdv -> {
            return usAdv.getUser().getId() == userId;
        }).collect(Collectors.toList());

        return userAdvertsOfUser;
    }

    @Override
    public UserAdvert findUserAdvertById(Long id) {
        return userAdvertsRepo.findById(id);
    }

    @Override
    public UserAdvert editUserAdvert(UserAdvert newUserAdvert, Long userAdvertId, Long userId) throws Exception {
        User theUser = usersRepo.findById(userId);
        newUserAdvert.setUser(theUser);

        //System.out.println(newUserInterest.getId()+ " " + newUserInterest.isActive());

        UserAdvert savedUserAdvert = userAdvertsRepo.editUserAdvert(userAdvertId, newUserAdvert);

        return savedUserAdvert;
    }

    @Override
    public UserAdvert addNewUserAdvert(UserAdvert userAdvert, Long userId) throws Exception {
        User theUser = usersRepo.findById(userId);
        userAdvert.setUser(theUser);

        UserAdvert savedUserAdvert = userAdvertsRepo.saveNewUserAdvert(userAdvert);

        return savedUserAdvert;
    }

    @Override
    public void deleteUserAdvertById(Long id) {
        userAdvertsRepo.deleteById(id);
    }
}
