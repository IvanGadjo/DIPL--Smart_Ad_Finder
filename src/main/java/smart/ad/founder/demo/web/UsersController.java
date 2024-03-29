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

    @GetMapping("/byEmail")
    public User getUserByEmail(@PathParam("email") String email){
        return usersService.findUserByEmail(email);
    }

    // ! Not tested: dodadov da se editira i user adverts listata
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

    @PostMapping("/test")
    public void testUser(){
        System.out.println("Projde");
    }

}
