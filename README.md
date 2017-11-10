# Electronic Program Guide for Android

[![Click for video](http://www.happanero.se/wp-content/uploads/2017/11/gh-epg.png)](http://www.happanero.se/wp-content/uploads/2017/11/epg-recording.mp4)

This is a "classic" TV EPG which works on tablets and phones and allows you to scroll in all directions (horizontal, vertical and diagonal).
Example project is located in repo but in short you need to add the EPG to your xml or by code:

```
<se.kmdev.tvepg.epg.EPG
    android:id="@+id/epg"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

Then by code adding a click listener...

```
epg.setEPGClickListener(new EPGClickListener() {
        @Override
        public void onChannelClicked(int channelPosition, EPGChannel epgChannel) {
            // Channel clicked
        }

        @Override
        public void onEventClicked(int channelPosition, int programPosition, EPGEvent epgEvent) {
            // Program event clicked
        }

        @Override
        public void onResetButtonClicked() {
            // Reset button clicked
        }
  });
```
... and data to be shown.

```
epg.setEPGData(new EPGDataImpl(MockDataService.getMockData()));
```

Thats basically it. 
If you want to use it in your project you need resources from the example project as well as the epg package for it to work. If you have any questions or such don't hesitate to contact me.

Good luck!

