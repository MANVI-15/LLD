package AbstractFactory;

public class  Main {
    public static void main(String[] args) {
        VehicleFactoryGenerator factoryGenerator=new VehicleFactoryGenerator();
        VehicleFactory luxury=factoryGenerator.getFactory("Luxury");
        VehicleFactory ordinary=factoryGenerator.getFactory("Ordinary");

        luxury.getVehicle("BMW");
        ordinary.getVehicle("Swift");
    }
}
