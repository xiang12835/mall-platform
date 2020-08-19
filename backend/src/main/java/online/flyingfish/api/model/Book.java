package online.flyingfish.api.model;

public class Book {

    private int id;

    private String name;

    private double bookPrice;

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

    public double getPrice() {
        return bookPrice;
    }

    public void setPrice(double price) {
        this.bookPrice = price;
    }

    @Override
    public String toString() {
        return "Book [BookId=" + id + ", BookName=" + name + ", BookPrice=" + bookPrice + "]";
    }
}
