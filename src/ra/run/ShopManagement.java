package ra.run;

import ra.config.InputMethods;
import ra.controller.CatalogController;
import ra.controller.UserController;
import ra.model.Catalog;
import ra.model.User;

public class ShopManagement {
    private static User user;
    private static UserController userController;
    private static CatalogController catalogController;

    public static void main(String[] args) {
        userController = new UserController();
        catalogController = new CatalogController();
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
        while (true) {
            System.out.println("==========Menu Admin=============");
            System.out.println("1. Quản lí tài khoản ");
            System.out.println("2. Quản lí danh mục");
            System.out.println("3. Quản lí sản phẩm");
            System.out.println("0. Đăng xuất");
            System.out.println("Nhập vao lựa chọn");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // quản lí tài khaoanr
                    break;
                case 2:
                    // quản lí danh mục
                    menuCatalog();
                    break;
                case 3:
                    break;
                case 0:
                    break;
                default:
                    System.err.println("Nhập sai , nhập lại");
            }
            if (choice == 0) {
                break;
            }
        }
    }

    public static void menuCatalog() {
        while (true) {
            System.out.println("==========Menu Catalog=============");
            System.out.println("1. Hiển thị danh sách danh mục ");
            System.out.println("2. Thêm mới danh mục");
            System.out.println("3. Chỉnh sửa thông");
            System.out.println("4. Khoa/Mo danh mục");
            System.out.println("0. Quay lại");
            System.out.println("Nhập vao lựa chọn");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    // danhh sách
                    displayAllCatalog();
                    break;
                case 2:
                    // thêm mới
                    createNewCatalog();
                    break;
                case 3:
                    // cập nhật
                    updateCatalog();
                    break;
                case 4:
//                       xóa
                    deleteCatalog();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("Nhập sai , nhập lại");
            }
            if (choice == 0) {
                break;
            }
        }
    }
    // hieenr thi
    public static void displayAllCatalog(){
        if(catalogController.findAll().isEmpty()){
            System.out.println("Danh sacsh trong");
            return;
        }
        for (Catalog cat:catalogController.findAll()) {
            System.out.println(cat);
        }
    }
    // theem mowis
    public static void createNewCatalog(){
        Catalog newCatalog =new Catalog();
        int idNew = catalogController.getNewId();
        System.out.println("ID New : "+idNew);
        newCatalog.setId(idNew);
        System.out.println("Nhập tên danh mục");
        newCatalog.setName(InputMethods.getString());
        // lưuu vào file
        catalogController.save(newCatalog);
    }
    public static void updateCatalog(){
        System.out.println("Nhập vào id của danh mục cần sửa");
        int idEdit = InputMethods.getInteger();
        Catalog editCatalog =catalogController.findById(idEdit);
        if(editCatalog==null){
            System.err.println("Không tôn tại id");
            return;
        }
        // cho phép sửa  thông tin
        System.out.println("Nhập tên danh mục mới");
        editCatalog.setName(InputMethods.getString());
        // lưuu vào file
        catalogController.save(editCatalog);
    }
    // xóa
    public static void deleteCatalog() {
        System.out.println("Nhập vào id của danh mục cần xóa");
        int idDel = InputMethods.getInteger();
        Catalog deleteCatalog = catalogController.findById(idDel);
        if (deleteCatalog == null) {
            System.err.println("Không tôn tại id");
            return;
        }
        // cho phép xóa
        // xóa luôn
//        catalogController.delete(idDel);
        // thay đổi trạng thái
        deleteCatalog.setStatus(!deleteCatalog.isStatus());
        catalogController.save(deleteCatalog);
    }
}
