package entity;


public class Cat {
    protected  int id;
    protected int price;
    protected String breed;
    protected String seller;

    public Cat() {
    }

    public Cat(int id) {
        this.id = id;
    }


    public Cat(int price, String breed, String seller) {
        this.price = price;
        this.breed = breed;
        this.seller = seller;
    }

    public Cat(int id, int price, String breed, String seller) {
        this.id = id;
        this.price = price;
        this.breed = breed;
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
