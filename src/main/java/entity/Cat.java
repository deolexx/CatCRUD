package entity;


public class Cat implements Comparable<Cat> {
    protected int id;
    protected int price;
    protected String breed;
    protected String seller_name;
    protected String seller_phone;

    public Cat() {
    }

    public Cat(int id) {
        this.id = id;
    }




    public Cat(int id, int price, String breed, String seller_name, String seller_phone) {
        this.id = id;
        this.price = price;
        this.breed = breed;
        this.seller_name = seller_name;
        this.seller_phone = seller_phone;
    }

    public Cat(int price, String breed, String seller_name, String seller_phone) {
        this.price = price;
        this.breed = breed;
        this.seller_name = seller_name;
        this.seller_phone = seller_phone;
    }


    public String getSeller_phone() {
        return seller_phone;
    }



    public int getId() {
        return id;
    }



    public int getPrice() {
        return price;
    }



    public String getBreed() {
        return breed;
    }



    public String getSeller_name() {
        return seller_name;
    }




    public int compareTo(Cat cat) {
        return this.getId() > cat.getId() ? 1 : -1;

    }

}
