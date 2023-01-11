import java.util.Random;

public class RiderGenerator implements Runnable {

    private final float meanRiderArrivalTime;
    private final BusStop busStop;
    private final Random randomGenerator;

    public RiderGenerator(float meanRiderArrivalTime, BusStop busStop) {
        this.meanRiderArrivalTime = meanRiderArrivalTime;
        this.busStop = busStop;
        randomGenerator = new Random();
    }

    @Override
    public void run() {
        int riderNumber = 1;
        while (!Thread.currentThread().isInterrupted()) {

            try {
                // Initializing and starting the bus threads
                Rider rider = new Rider(riderNumber, busStop);
                (new Thread(rider)).start();
                riderNumber++;
                // Sleeping the thread to obtain the inter arrival time between the rider threads
                Thread.sleep(getRiderArrivalInterval());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private long getRiderArrivalInterval() {
        float lambda = 1 / (1000 * meanRiderArrivalTime);
        float u = 1.0f - randomGenerator.nextFloat();
        return Math.round(-Math.log(u) / lambda) ;
    }
}
