package Strategy.With;

public class OffRoadVehicle extends Vehicle{

    OffRoadVehicle()
    {
        super(new SpecialDriveStrategy());
    }
}
