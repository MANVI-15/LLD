package AbstractFactory;

public class OrdinaryVehicleFactory implements VehicleFactory {

    public Vehicle getVehicle(String input){

        if(input.equals("Maruti"))
        {
            return new Maruti();
        }
        else if(input.equals("Swift"))
        {
            return new Swift();
        }
        return null;
    }
}
