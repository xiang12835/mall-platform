package online.flyingfish.api.controller;

import online.flyingfish.api.model.User;
import online.flyingfish.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/user", method = RequestMethod.GET)
    public List<User> UserQry() {
        return userService.getUser();
    }

    @RequestMapping(value ="/user/getName", method = RequestMethod.GET)
    public String getUserName(@RequestParam(value="id") int userId) {
        return userService.getUserName(userId);
    }

    @RequestMapping(value ="/user", method = RequestMethod.POST)
    public void UserAdd(User user){
         userService.addUser(user);
    }

    @RequestMapping(value ="/user", method = RequestMethod.PUT)
    public void UserUpdate(User user){
        userService.updateUserById(user);
    }

    @RequestMapping(value ="/user", method = RequestMethod.DELETE)
    public void UserDel(@RequestParam(value="id") int id){
        userService.delUserById(id);
    }


}
