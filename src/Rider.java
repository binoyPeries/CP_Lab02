import java.util.concurrent.Semaphore;

public class Rider implements Runnable {

    private final int riderNumber;
    private final BusStop busStop;

    public Rider(int riderNumber, BusStop busStop) {
        this.riderNumber = riderNumber;
        this.busStop = busStop;
    }

    @Override
    public void run() {
        try {
            Semaphore mutex = busStop.getWaitingMutex();
            Semaphore busSemaphore = busStop.getBusSemaphore();
            Semaphore boardedSemaphore = busStop.getBoardedSemaphore();

            mutex.acquire();
            busStop.incrementWaitingRiders();
            mutex.release();

            busSemaphore.acquire();
            board();
            boardedSemaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void board() {
        System.out.println("Rider number " + riderNumber + " has boarded.");
    }
}
