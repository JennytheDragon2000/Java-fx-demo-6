package Model;

import java.io.Serializable;

public class customerTM implements Serializable {
    private String id;
    private String name;
    private String address;

    public customerTM(){
//        this.id = null;
//        this.name = null;
//        this.address = null;
    }

    public customerTM(String iD, String name, String address) {
        this.id = iD;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "customerTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
