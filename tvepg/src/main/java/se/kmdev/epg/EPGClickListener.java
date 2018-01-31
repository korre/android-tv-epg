package se.kmdev.epg;

import se.kmdev.epg.domain.EPGChannel;
import se.kmdev.epg.domain.EPGEvent;

/**
 * Created by Kristoffer on 15-05-25.
 */
public interface EPGClickListener {

    void onChannelClicked(int channelPosition, EPGChannel epgChannel);

    void onEventClicked(int channelPosition, int programPosition, EPGEvent epgEvent);

    void onResetButtonClicked();
}
