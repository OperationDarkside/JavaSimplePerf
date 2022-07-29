public class Checkpoint {
    private String name;
    private long timestamp;

    public Checkpoint(String name) {
        this.name = name;
        this.timestamp = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }
    public long getTimestamp() {
        return timestamp;
    }
}
