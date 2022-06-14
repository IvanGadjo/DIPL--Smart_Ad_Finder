package smart.ad.founder.demo.application.service;

import smart.ad.founder.demo.domain.model.entities.User;

import java.util.List;

public interface UsersService {

    List<User> findAllUsers();

    User findUserById(Long id);

    User findUserByEmail(String email);

    User editUser(User newUser);

    User createNewUser(User user);

    void deleteUserById(Long id);
}
