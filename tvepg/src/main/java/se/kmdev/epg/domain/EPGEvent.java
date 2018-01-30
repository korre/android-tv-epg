package se.kmdev.epg.domain;

/**
 * Created by Kristoffer.
 */
public class EPGEvent {

    private final long start;
    private final long end;
    private final String title;
    private boolean selected;

    public EPGEvent(long start, long end, String title) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.selected = false;
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

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
