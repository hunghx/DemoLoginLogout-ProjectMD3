package ra.model;

import java.io.Serializable;

public class Catalog implements Serializable {
    private int id;
    private String name;
    private boolean status = true;

    public Catalog() {
    }

    public Catalog(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Id : "+id + "| Name : "+name + "|Status : "+(status?"Active":"InActive") ;
    }
}