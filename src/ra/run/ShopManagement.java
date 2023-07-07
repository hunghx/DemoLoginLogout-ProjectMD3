package ra.run;

import ra.config.InputMethods;
import ra.controller.UserController;
import ra.model.User;

public class ShopManagement {
    private static User user;
    private static UserController userController;

    public static void main(String[] args) {
        userController = new UserController();
        while (true) {
            System.out.println("----------------menu---------------");
            System.out.println("1. Login");
            System.out.println("2. Regiter");
            System.out.println("3. Exit");
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // login
                    login();
                    break;
                case 2:
                    // regsiter
                    register();
                    break;
                case 3:
                    // thoats
                    System.exit(0);
                    break;
                default:
                    System.err.println("invalid number , please enter 1 to 3");
            }
        }
    }

    public static void login() {
        System.out.println("Enter username");
        String username = InputMethods.getString();
        System.out.println("Enter password");
        String password = InputMethods.getString();
        User userLogin = userController.login(username, password);
        if (userLogin == null) {
            // sai tk hoac mat khau
            System.err.println("Incorrect username or password");
            return;
        }
        if (userLogin.getRole().equalsIgnoreCase("admin")) {
            // chuyeenr ddeens menu admin
            user = userLogin;
            menuAdmin();
        } else {
            if (userLogin.isStatus()) {
                // chuyen den trang nguoi dung

                user = userLogin;
                menuUser();
            } else {
                System.err.println("Account is blocked");
            }
        }
    }

    public static void register() {
        User userNew = new User();
        int id = userController.getNewId();
        System.out.println("ID : " + id);
        userNew.setId(id);
        System.out.println("Enter your fullname");
        userNew.setFullName(InputMethods.getString());
        System.out.println("Enter Username");
        while (true) {
            String username = InputMethods.getString();
            if (userController.checkExistUserName(username)) {
                System.err.println("Username is existed , enter other name");
                continue;
            }
            userNew.setUsername(username);
            break;
        }
        System.out.println("Enter password");
        userNew.setPassword(InputMethods.getString());
        userNew.setRole("user");
        userController.save(userNew);
        System.out.println("Register success, please login");
        login();
    }

    public static void logout() {
        user = null;
    }

    public static void menuUser() {
        System.out.println("Welcome to my shop, " + user.getFullName());
        InputMethods.pressAnyKey();
        while (true) {

            System.out.println("----------------menu user---------------");
            System.out.println("1. Logout");
            System.out.println("Enter your choice");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    logout();
                    return;
                case 2:


                    break;
                case 3:
                    break;
                default:
                    System.err.println("invalid number , please enter 1 to 3");
            }
        }
    }

    public static void menuAdmin() {
        System.out.println("Welcome to back shop, " + user.getUsername());
        InputMethods.pressAnyKey();
    }

}
