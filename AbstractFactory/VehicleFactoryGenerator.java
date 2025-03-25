package AbstractFactory;

public class VehicleFactoryGenerator {

    public VehicleFactory getFactory(String input){

        if(input.equals("Luxury"))
        {
            return new LuxuryVehicleFactory();
        }
        else if(input.equals("Ordinary")){
            return new OrdinaryVehicleFactory();
        }
        return null;
    }
}
