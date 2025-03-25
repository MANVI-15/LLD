package Strategy.With;

public class SportyVehicle extends Vehicle {

    SportyVehicle(){
        super(new SpecialDriveStrategy());
    }
}
