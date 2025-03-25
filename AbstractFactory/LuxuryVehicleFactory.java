package AbstractFactory;

public class LuxuryVehicleFactory implements VehicleFactory{

    public Vehicle getVehicle(String input){

        if(input.equals("BMW"))
        {
            return new BMW();
        }
        else if(input.equals("Mercedes"))
        {
            return new Mercedes();
        }
        return null;
    }
}
