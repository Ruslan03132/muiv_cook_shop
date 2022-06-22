package Model;

public class Menu {
    private int id;
    private String title;
    private int price;
    private int weight;

    public Menu(int id, String title, int price, int weight) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.weight = weight;
    }

    public Menu() {
    }

    public Menu(String title, int price, int weight) {
        this.title = title;
        this.price = price;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
class Users {
    private int id,ip;
    private String fullName;

    public Users() {
    }

    public Users(int id, int ip, String fullName) {
        this.id = id;
        this.ip = ip;
        this.fullName = fullName;
    }

    public Users(int ip, String fullName) {
        this.ip = ip;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
class Order{

}
class Supply{

}
class Product{

}
