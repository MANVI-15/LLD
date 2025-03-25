package Decorator;

public class Main {
    public static void main(String[] args) {

        // Mushroom+VegieDelight
        BasePizza mushroom = new Mushroom(new VegieDelight());

        System.out.println(mushroom.cost());
    }
}
