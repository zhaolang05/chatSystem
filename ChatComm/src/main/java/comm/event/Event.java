package comm.event;

public class Event {
    private final Object source;
    private final String type;
    private final Object data;
    public Event(Object source, String type, Object data) {
        this.source = source;
        this.type = type;
        this.data = data;
    }
    public Object getSource() {
        return source;
    }
    public String getType() {
        return type;
    }
    public Object getData() {
        return data;
    }
}