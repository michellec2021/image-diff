package util;

/**
 * @author neo
 */
public final class StopWatch {
    private long start;

    public StopWatch() {
        this.reset();
    }

    public void reset() {
        this.start = System.nanoTime();
    }

    public long elapsed() {
        long end = System.nanoTime();
        return end - this.start;
    }
}
