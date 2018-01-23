package se.kmdev.epg.domain;

/**
 * Created by Kristoffer.
 */
public class EPGEvent {

    private final long start;
    private final long end;
    private final String title;

    public EPGEvent(long start, long end, String title) {
        this.start = start;
        this.end = end;
        this.title = title;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCurrent() {
        long now = System.currentTimeMillis();
        return now >= start && now <= end;
    }
}
