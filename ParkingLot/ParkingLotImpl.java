package Questions.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;

enum SpotStatus {AVAILABLE,OCCUPIED}
enum SpotType {TWO_WHEELER,FOUR_WHEELER}
enum VehicleType {TWO_WHEELER,FOUR_WHEELER}

abstract class Vehicle{
    VehicleType type;
    Vehicle(VehicleType type){
        this.type=type;
    }
    VehicleType getVehicleType(){
        return type;
    }
}

class Car extends Vehicle {
    Car(VehicleType type){
        super(VehicleType.FOUR_WHEELER);
    }
}
class Bike extends  Vehicle{
    Bike(VehicleType type) {
        super(VehicleType.TWO_WHEELER);
    }
}

class Ticket{
    int entry_time;
    int spot_id;

    Ticket(int time,int id)
    {
        this.entry_time=time;
        this.spot_id=id;
    }

    int getEntry_time(){
        return entry_time;
    }

    int getSpot_id(){
        return spot_id;
    }
}

interface PaymentStrategy{
    int getPayment(int exit_time,int entry_time,int per_hour_cost);
}

class HourlyPaymentStrategy implements PaymentStrategy{

    public int getPayment(int exit_time,int entry_time,int per_hour_cost){
        return (exit_time-entry_time)*per_hour_cost;
    }

}

class MinutesPaymentStrategy implements PaymentStrategy{
    public int getPayment(int exit_time,int entry_time,int per_minute_cost){
       // implement per minute strategy
        return 0;
    }
}

class PaymentManager{
    PaymentStrategy paymentStrategy;

    static PaymentManager instance;

    private PaymentManager(){}

    public static PaymentManager getInstance(){
        if(instance==null)
        {
            instance=new PaymentManager();
        }
        return instance;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy)
    {
        this.paymentStrategy=paymentStrategy;
    }

    int getPayment(int exit_time,int entry_time,int cost)
    {
        return this.paymentStrategy.getPayment(exit_time,entry_time,cost);
    }

}

class SpotManager
{
    HashMap<Integer,Spot>allSpots = new HashMap<>();
    int idx=0;
    static SpotManager instance;

    private SpotManager(){}

    public static SpotManager getInstance(){
        if(instance==null)
        {
            instance=new SpotManager();
        }
        return instance;
    }


    Spot createSpot(SpotType type,SpotStatus status){
        Spot s=new Spot(status,type,idx);
        allSpots.put(idx++,s);
        return s;
    }

    Spot getSpotAtId(int id)
    {
        return allSpots.get(id);
    }
}

class Spot{
    SpotStatus status;
    SpotType type;
    int id;

    Spot(SpotStatus status,SpotType type,int id)
    {
        this.status=status;
        this.type=type;
        this.id=id;
    }

    SpotStatus getStatus()
    {
        return status;
    }

    void setStatus(SpotStatus status){
        this.status=status;
    }

    SpotType getType(){
        return type;
    }

    int getId()
    {
        return id;
    }
}
class ParkingLot{
    ArrayList<Spot>twoWheelerSpots;
    ArrayList<Spot>fourWheelerSpots;
    int costTwoWheeler ;
    int costFourWheeler ;
    static ParkingLot instance;
    static SpotManager spotManager;
    static PaymentManager paymentManager;

    private ParkingLot(){}

    public static ParkingLot getInstance(){
        if(instance==null)
        {
            instance=new ParkingLot();
        }
        return instance;
    }

    public void initializeParkingLot(int no_of_two_wheeler_spots,int no_of_four_wheeler_spots,int costTwoWheeler,int costFourWheeler,PaymentStrategy paymentStrategy){
        this.twoWheelerSpots = new ArrayList<>(no_of_two_wheeler_spots);
        this.fourWheelerSpots = new ArrayList<>(no_of_four_wheeler_spots);
        spotManager = SpotManager.getInstance();
        paymentManager=PaymentManager.getInstance();
        paymentManager.setPaymentStrategy(paymentStrategy);

        for(int i=0;i<no_of_two_wheeler_spots;i++)
        {
            twoWheelerSpots.add(spotManager.createSpot(SpotType.TWO_WHEELER,SpotStatus.AVAILABLE));
        }

        for(int i=0;i<no_of_four_wheeler_spots;i++)
        {
            fourWheelerSpots.add(spotManager.createSpot(SpotType.FOUR_WHEELER,SpotStatus.AVAILABLE));
        }

        this.costFourWheeler=costFourWheeler;
        this.costTwoWheeler=costTwoWheeler;
    }

    private Spot isTwoWheelerSpotEmpty(){
        for(int i=0;i<twoWheelerSpots.size();i++)
        {
            if(twoWheelerSpots.get(i).getStatus()==SpotStatus.AVAILABLE)
            {
                return twoWheelerSpots.get(i);
            }
        }
        return null;
    }

    private Spot isFourWheelerSpotEmpty(){
        for(int i=0;i<fourWheelerSpots.size();i++)
        {
            if(fourWheelerSpots.get(i).getStatus()==SpotStatus.AVAILABLE)
            {
                return fourWheelerSpots.get(i);
            }
        }
        return null;
    }

    Ticket entry(Vehicle vehicle){
        int entry_time=1;

        if(vehicle.getVehicleType()==VehicleType.TWO_WHEELER)
        {
            Spot emptySpot=isTwoWheelerSpotEmpty();
            if(emptySpot!=null)
            {
                emptySpot.setStatus(SpotStatus.OCCUPIED);
                System.out.println("Vehicle has been entered");
                return new Ticket(entry_time++,emptySpot.getId());
            }
            else {
                System.out.println("Space not available");
            }
        }
        else if(vehicle.getVehicleType()==VehicleType.FOUR_WHEELER)
        {
            Spot emptySpot=isFourWheelerSpotEmpty();
            if(emptySpot!=null)
            {
                 emptySpot.setStatus(SpotStatus.OCCUPIED);
                 System.out.println("Vehicle has been entered");
                 return new Ticket(entry_time++,emptySpot.getId());
            }
            else {
                System.out.println("Space not available");
            }
        }
        else {
            System.out.println("Parking not available for this vehicle");

        }

        return null;

    }

    void exit(Ticket ticket){
        int exit_time=2;
        int spot_type=ticket.getSpot_id();
        Spot emptySpot=spotManager.getSpotAtId(spot_type);
        int cost=0;

        if(emptySpot.getType()==SpotType.TWO_WHEELER)
        {
           cost = paymentManager.getPayment(exit_time,ticket.getEntry_time(),costTwoWheeler);
        }
        else if(emptySpot.getType()==SpotType.FOUR_WHEELER)
        {
           cost= paymentManager.getPayment(exit_time,ticket.getEntry_time(),costFourWheeler);
        }

        System.out.println("Pay this amount " +cost);
        emptySpot.setStatus(SpotStatus.AVAILABLE);
    }

}
public class ParkingLotImpl {

    public static void main(String[] args){

        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.initializeParkingLot(3,4,20,40, new HourlyPaymentStrategy());

        Vehicle car= new Car(VehicleType.FOUR_WHEELER);
        Vehicle bike=new Bike(VehicleType.TWO_WHEELER);

        Ticket t=parkingLot.entry(car);
        parkingLot.exit(t);

        Ticket p=parkingLot.entry(bike);
        parkingLot.exit(p);
    }
}
