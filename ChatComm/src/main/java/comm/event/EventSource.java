package comm.event;




import java.util.ArrayList;

import java.util.List;

public class EventSource {
    private final List<EventListener> listeners = new ArrayList<>();
    public void addEventListener(EventListener listener) {
        listeners.add(listener);
    }
    public void removeEventListener(EventListener listener) {
        listeners.remove(listener);
    }
    public void fireEvent(Event event) {
        for (EventListener listener : listeners) {
            listener.handleEvent(event);
        }
    }

    public List<EventListener> getListeners() {
        return listeners;
    }
}