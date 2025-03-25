package Strategy.With;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle=new SportyVehicle();
        vehicle.drive();

        Vehicle vehicle1=new PassengerVehicle();
        vehicle1.drive();
    }
}
