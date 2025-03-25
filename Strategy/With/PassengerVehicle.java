package Strategy.With;

public class PassengerVehicle extends Vehicle{

    PassengerVehicle(){
        super(new NormalDriveStrategy());
    }
}
