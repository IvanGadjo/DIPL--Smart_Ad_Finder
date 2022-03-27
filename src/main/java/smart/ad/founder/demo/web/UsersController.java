package smart.ad.founder.demo.web;


import org.springframework.web.bind.annotation.*;
import smart.ad.founder.demo.application.service.UsersService;
import smart.ad.founder.demo.domain.model.entities.User;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return usersService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id){
        return usersService.findUserById(id);
    }

    @PatchMapping("/editUser")
    public User editUser(@RequestBody User newUser){
        return usersService.editUser(newUser);
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return usersService.createNewUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        usersService.deleteUserById(id);
    }

}
