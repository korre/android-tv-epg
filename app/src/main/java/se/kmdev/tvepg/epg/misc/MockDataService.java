package se.kmdev.tvepg.epg.misc;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import se.kmdev.tvepg.epg.EPG;
import se.kmdev.tvepg.epg.domain.EPGChannel;
import se.kmdev.tvepg.epg.domain.EPGEvent;

/**
 * Created by Kristoffer on 15-05-24.
 */
public class MockDataService {

    private static Random rand = new Random();
    private static List<Integer> availableEventLength = Lists.newArrayList(
            1000*60*15,  // 15 minutes
            1000*60*30,  // 30 minutes
            1000*60*45,  // 45 minutes
            1000*60*60,  // 60 minutes
            1000*60*120  // 120 minutes
    );

    private static List<String> availableEventTitles = Lists.newArrayList(
            "Avengers",
            "How I Met Your Mother",
            "Silicon Valley",
            "Late Night with Jimmy Fallon",
            "The Big Bang Theory",
            "Leon",
            "Die Hard"
    );

    private static List<String> availableChannelLogos = Lists.newArrayList(
            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/SVT1_logo_2012.svg/1280px-SVT1_logo_2012.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/SVT2_logo_2012.svg/1280px-SVT2_logo_2012.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/TV3_Viasat.svg/1024px-TV3_Viasat.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/TV4sweden_logo.svg/1024px-TV4sweden_logo.svg.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Kanal_5_Sweden.svg/150px-Kanal_5_Sweden.svg.png"
    );

    public static Map<EPGChannel, List<EPGEvent>> getMockData() {
        HashMap<EPGChannel, List<EPGEvent>> result = Maps.newLinkedHashMap();

        long nowMillis = System.currentTimeMillis();

        for (int i=0 ; i < 20 ; i++) {
            EPGChannel epgChannel = new EPGChannel(availableChannelLogos.get(i % 5),
                    "Channel " + (i+1), Integer.toString(i));

            result.put(epgChannel, createEvents(epgChannel, nowMillis));
        }

        return result;
    }

    private static List<EPGEvent> createEvents(EPGChannel epgChannel, long nowMillis) {
        List<EPGEvent> result = Lists.newArrayList();

        long epgStart = nowMillis - EPG.DAYS_BACK_MILLIS;
        long epgEnd = nowMillis + EPG.DAYS_FORWARD_MILLIS;

        long currentTime = epgStart;

        while (currentTime <= epgEnd) {
            long eventEnd = getEventEnd(currentTime);
            EPGEvent epgEvent = new EPGEvent(currentTime, eventEnd, availableEventTitles.get(randomBetween(0, 6)));
            result.add(epgEvent);
            currentTime = eventEnd;
        }

        return result;
    }

    private static long getEventEnd(long eventStartMillis) {
        long length = availableEventLength.get(randomBetween(0,4));
        return eventStartMillis + length;
    }

    private static int randomBetween(int start, int end) {
        return start + rand.nextInt((end - start) + 1);
    }
}
