package ra.service;

import ra.config.IOFile;
import ra.model.User;

import java.util.List;

public class UserService implements IGenericSevice<User,Integer> {
    private IOFile<User> userData;
    private List<User> listUser;
    public UserService() {
        this.userData = new IOFile<>();
        this.listUser= userData.readFromFile(IOFile.USER_PATH);
    }
    @Override
    public List<User> findAll() {
        return listUser;
    }

    @Override
    public void save(User user) {
        if(findById(user.getId())==null){
            // thêm mới
            listUser.add(user);
        }else {
            // cập nhât
            listUser.set(listUser.indexOf(findById(user.getId())),user);
        }
        // lưu sự thayddooir vào file
        userData.writeToFile(listUser,IOFile.USER_PATH);
    }

    @Override
    public void delete(Integer integer) {
        listUser.remove(findById(integer));
        userData.writeToFile(listUser,IOFile.USER_PATH);
    }

    @Override
    public User findById(Integer integer) {
        for (User u:listUser
        ) {
            if(u.getId()==integer){
                return u;
            }
        }
        return  null;
    }
    public User login(String username, String password){
        for (User u:listUser
             ) {
            if(u.getUsername().equals(username)&& u.getPassword().equals(password)){
                return u;
            }
        }
        return  null;
    }
    public int getNewId(){
        int max=0;
        for (User u :listUser) {
            if(u.getId()>max){
                max= u.getId();
            }
        }
        return max+1;
    }
    public boolean checkExistUserName(String username){
        for (User u :listUser) {
            if(u.getUsername().equals(username)){
                return true;
            }
        }
        return  false;
    }
}
