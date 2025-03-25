package Prototype;

public class Main {
    public static void main(String[] args) {
        Product product=new Product("cream",10);
        Product clone=(Product) product.clone();

        clone.display();
    }
}
