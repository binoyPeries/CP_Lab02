import java.util.concurrent.Semaphore;

public class BusStop {

    // number of riders in the boarding area waiting for a bus
    private int waitingRiders = 0;
    private final int maximumBusCapacity;

    // semaphore to protect the waitingRiders variable
    private final Semaphore waitingMutex = new Semaphore(1);

    // semaphore to indicate to the passengers that the bus has arrived
    private final Semaphore busSemaphore = new Semaphore(0);

    // semaphore to indicate that a passenger has boarded
    private final Semaphore boardedSemaphore = new Semaphore(0);

    public BusStop(int maximumBusCapacity) {
        this.maximumBusCapacity = maximumBusCapacity;
    }

    public int getWaitingRiders() {
        return waitingRiders;
    }

    public void setWaitingRiders(int waitingRiders) {
        this.waitingRiders = waitingRiders;
    }

    public void incrementWaitingRiders() {
        this.waitingRiders++;
    }

    public int getMaximumBusCapacity() {
        return maximumBusCapacity;
    }

    public Semaphore getWaitingMutex() {
        return waitingMutex;
    }

    public Semaphore getBusSemaphore() {
        return busSemaphore;
    }

    public Semaphore getBoardedSemaphore() {
        return boardedSemaphore;
    }


}
