public class Ride {

    public final int time;
    public  double distance;
    public RideType rideType;

  /*  public Ride(double distance, int time) {
        this.distance=distance;
        this.time=time;
    }*/

    public enum RideType{

        NORMAL,PREMIUM;
    }


    public Ride(RideType rideType ,double distance, int time) {
        this.rideType=rideType;
        this.distance=distance;
        this.time=time;
    }
}
