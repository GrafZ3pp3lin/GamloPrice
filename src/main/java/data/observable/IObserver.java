package data.observable;

/**
 * Observer can be notified by Sender
 */
public interface IObserver {

    /**
     * update the GUI depends on Update Type
     *
     * @param type defines GUI Pane to been shown
     * @param id   id of Question
     */
    void update(UpdateType type, String id);

}