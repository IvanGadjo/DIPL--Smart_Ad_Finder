package smart.ad.founder.demo.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smart.ad.founder.demo.application.repo.UsersRepo;
import smart.ad.founder.demo.application.service.UsersService;
import smart.ad.founder.demo.domain.model.entities.User;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    UsersRepo usersRepo;

    public UsersServiceImpl(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Override
    public List<User> findAllUsers() {
        return usersRepo.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return usersRepo.findById(id);
    }

    @Override
    public User editUser(User newUser) {
        return usersRepo.editUser(newUser);
    }

    @Override
    public User createNewUser(User user) {
//        User possibleCreatedUser = usersRepo.findById(user.getId());
        User possibleCreatedUser = usersRepo.findById_2(user.getId());
        if(possibleCreatedUser == null){
            User usr = usersRepo.createNewUser(user);
            System.out.println("IF_____________________" + usr.getUserEmail());
            return usr;
        }
        else {
            System.out.println("ELSE______________________" + possibleCreatedUser.getUserEmail());
            return possibleCreatedUser;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        usersRepo.deleteById(id);
    }
}
