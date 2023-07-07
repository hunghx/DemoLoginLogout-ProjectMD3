package ra.run;

import ra.config.IOFile;
import ra.model.User;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        IOFile<User> IO = new IOFile<>();
        User user = new User(2,"'Hung Ho","hunghx","123456","user");
        User admin = new User();
        admin.setId(1);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole("admin");
        List<User> list= new ArrayList<>();
        list.add(user);
        list.add(admin);
        IO.writeToFile(list,IOFile.USER_PATH);
    }
}
