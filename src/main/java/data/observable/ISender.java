package data.observable;

/**
 * Sender notifies its Observer on some Events
 */
public interface ISender {

    /**
     * notify Observer
     */
    void notifyObserver();

    /**
     * set Observer to this Event Sender
     *
     * @param observer
     */
    void setObserver(IObserver observer);
}
