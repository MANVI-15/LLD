package Strategy.With;

public class SpecialDriveStrategy implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Special capability");
    }
}
