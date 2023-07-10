package ra.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile<T> {
    public  static final String CATALOG_PATH = "C:\\Users\\hung1\\OneDrive\\Desktop\\ProjectMD3-JV01\\src\\ra\\data\\catalogs.txt";
    public  static final String USER_PATH = "C:\\Users\\hung1\\OneDrive\\Desktop\\ProjectMD3-JV01\\src\\ra\\data\\users.txt";

    public  static final String PRODUCT_PATH = "C:\\Users\\hung1\\OneDrive\\Desktop\\ProjectMD3-PTJV1\\src\\ra\\data\\product";
    public void writeToFile(List<T> list, String path){
        // sử dụng ghi file binary
        try {
            FileOutputStream fos= new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<T> readFromFile(String path){
        // sử dụng ghi file binary
        try {
            FileInputStream fis= new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<T> list =(List<T>) ois.readObject();
            return list;
        }catch (EOFException x){
            // không cần xử lí
        }
        catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException c){
            c.printStackTrace();
        }
        return new ArrayList<>();
    }
}
