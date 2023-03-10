public class Main {
    public static void main(String[] args) {

        // mean time between riders in seconds
        final float meanRiderArrivalTime = 30;

        // mean time between buses in seconds
        final float meanBusArrivalTime = 1200;

        final int busCapacity = 50;

        BusStop busStop = new BusStop(busCapacity);

        RiderGenerator riderGenerator = new RiderGenerator(meanRiderArrivalTime, busStop);
        (new Thread(riderGenerator)).start();

        BusGenerator busGenerator = new BusGenerator(meanBusArrivalTime, busStop);
        (new Thread(busGenerator)).start();
    }
}