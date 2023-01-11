import java.util.concurrent.Semaphore;

public class Bus implements Runnable {

    private final int busNumber;
    private final BusStop busStop;

    public Bus(int busNumber, BusStop busStop) {
        this.busNumber = busNumber;
        this.busStop = busStop;
    }

    @Override
    public void run() {
        try {
            Semaphore mutex = busStop.getWaitingMutex();
            Semaphore busSemaphore = busStop.getBusSemaphore();
            Semaphore boardedSemaphore = busStop.getBoardedSemaphore();

            mutex.acquire();
            arrive();
            int waitingRiders = busStop.getWaitingRiders();

            int n = Math.min(waitingRiders, busStop.getMaximumBusCapacity());

            for (int i = 0; i < n; i++) {
                busSemaphore.release();
                boardedSemaphore.acquire();
            }
            busStop.setWaitingRiders(Math.max(0, 50 - waitingRiders));
            mutex.release();
            depart();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void arrive() {
        System.out.println("Bus " + busNumber + " has arrived.");
    }
    void depart() {
        System.out.println("Bus " + busNumber + " has departed.");
    }
}
