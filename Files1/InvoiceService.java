public class InvoiceService {

    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_FARE = 5;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER =15 ;
    private static final int PREMIUM_COST_PER_TIME = 2;
    private static final double PREMIUM_MINIMUM_FARE = 20;

    private  RideRepository rideRepository;


    public InvoiceService() {
        this.rideRepository = new  RideRepository();
    }

    public double calculateFare(double distance, int time) {
        double totalFare =  distance*MINIMUM_COST_PER_KILOMETER +time*COST_PER_TIME;
      // if(totalFare < MINIMUM_FARE)
        //   return MINIMUM_FARE;
       return Math.max(totalFare,MINIMUM_FARE);
    }

    private double calculatePremiumFare(double distance, int time) {
        double totalFare =  distance*PREMIUM_MINIMUM_COST_PER_KILOMETER +time*PREMIUM_COST_PER_TIME;
        return Math.max(totalFare,PREMIUM_MINIMUM_FARE);
    }


    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for(Ride ride:rides) {
            if(ride.rideType.equals(Ride.RideType.NORMAL)){
                totalFare += this.calculateFare(ride.distance, ride.time); 
            }
            else {
                totalFare += this.calculatePremiumFare(ride.distance, ride.time);
            }
        }
        System.out.println(totalFare);
        return  new InvoiceSummary(rides.length,totalFare);
    }

 
    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
