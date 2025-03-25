package Factory;

public class ShapeFactory {

    public static Shape getShape(String input){
            if(input.equals("RECTANGLE")){
                return new Rectangle();
            }
            else if(input.equals("SQUARE")){
                return new Square();
            }
           return null;
    }
}
