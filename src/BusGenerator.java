import java.util.Random;

public class BusGenerator implements Runnable{

    private final float meanBusArrivalTime;
    private final BusStop busStop;

    private final Random randomGenerator;

    public BusGenerator(float meanBusArrivalTime, BusStop busStop) {
        this.meanBusArrivalTime = meanBusArrivalTime;
        this.busStop = busStop;
        randomGenerator = new Random();
    }

    @Override
    public void run() {

        int busNumber = 1;
        while (!Thread.currentThread().isInterrupted()) {

            try {
                Bus bus = new Bus(busNumber, busStop);
                (new Thread(bus)).start();

                busNumber++;

                // Sleeping the thread for the time between buses
                Thread.sleep(getBusArrivalInterval());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

//    In an exponential distribution, given the mean, the time interval between two events,
//    the following equation can be used.
//    time gap = -ln(U) / λ
//    U = random variable that follows a uniform distribution in the range of [0,1)
//    λ = 1/mean

    private long getBusArrivalInterval() {
        float lambda = 1/meanBusArrivalTime;
        float u = 1.0f - randomGenerator.nextFloat();
        return Math.round(-Math.log(u) / lambda) * 1000;
    }
}
