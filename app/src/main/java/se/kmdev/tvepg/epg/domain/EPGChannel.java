package se.kmdev.tvepg.epg.domain;

/**
 * Created by Kristoffer.
 */
public class EPGChannel {

    private final String channelID;
    private final String name;
    private final String imageURL;

    public EPGChannel(String imageURL, String name, String channelID) {
        this.imageURL = imageURL;
        this.name = name;
        this.channelID = channelID;
    }

    public String getChannelID() {
        return channelID;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }
}
