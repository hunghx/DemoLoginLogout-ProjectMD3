package ra.controller;

import ra.model.User;
import ra.service.UserService;

public class UserController {
    private UserService userService = new UserService();
    public User login(String username, String password){
        return userService.login(username,password);
    }
    public boolean checkExistUserName(String username){
        return userService.checkExistUserName(username);
    }
    public void save(User user) {
        userService.save(user);
    }
    public int getNewId(){
        return userService.getNewId();
    }
}
