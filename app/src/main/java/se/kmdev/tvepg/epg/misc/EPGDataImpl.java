package se.kmdev.tvepg.epg.misc;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

import se.kmdev.tvepg.epg.EPGData;
import se.kmdev.tvepg.epg.domain.EPGChannel;
import se.kmdev.tvepg.epg.domain.EPGEvent;

/**
 * TODO: Add null check when fetching at position etc.
 * Created by Kristoffer on 15-05-23.
 */
public class EPGDataImpl implements EPGData {

    private List<EPGChannel> channels = Lists.newArrayList();
    private List<List<EPGEvent>> events = Lists.newArrayList();

    public EPGDataImpl(Map<EPGChannel, List<EPGEvent>> data) {
        channels = Lists.newArrayList(data.keySet());
        events = Lists.newArrayList(data.values());
    }

    public EPGChannel getChannel(int position) {
        return channels.get(position);
    }

    public List<EPGEvent> getEvents(int channelPosition) {
        return events.get(channelPosition);
    }

    public EPGEvent getEvent(int channelPosition, int programPosition) {
        return events.get(channelPosition).get(programPosition);
    }

    public int getChannelCount() {
        return channels.size();
    }

    @Override
    public boolean hasData() {
        return !channels.isEmpty();
    }
}
