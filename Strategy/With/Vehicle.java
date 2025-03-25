package Strategy.With;

public class Vehicle {
    private DriveStrategy obj;

    Vehicle(DriveStrategy obj)
    {
        this.obj=obj;
    }

    public void drive(){
        obj.drive();
    }
}
