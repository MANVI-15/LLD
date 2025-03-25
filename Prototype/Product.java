package Prototype;

public class Product implements Prototype{

    String name;
    int price;

    Product(String name,int price)
    {
        this.name=name;
        this.price=price;
    }

    @Override
    public Prototype clone() {
        return new Product(this.name,this.price);
    }

    public void display() {
        System.out.println("Product: " + this.name);
        System.out.println("Price: $" + this.price);
    }
}
