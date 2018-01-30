package se.kmdev.tvepg;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import se.kmdev.epg.EPG;
import se.kmdev.epg.EPGClickListener;
import se.kmdev.epg.EPGData;
import se.kmdev.epg.domain.EPGChannel;
import se.kmdev.epg.domain.EPGEvent;
import se.kmdev.epg.misc.EPGDataImpl;
import se.kmdev.epg.misc.MockDataService;


public class MainActivity extends AppCompatActivity {

    private EPG epg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        epg = (EPG) findViewById(R.id.epg);
        epg.setEPGClickListener(new EPGClickListener() {
            @Override
            public void onChannelClicked(int channelPosition, EPGChannel epgChannel) {
                Toast.makeText(MainActivity.this, epgChannel.getName() + " clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEventClicked(int channelPosition, int programPosition, EPGEvent epgEvent) {
                Toast.makeText(MainActivity.this, epgEvent.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResetButtonClicked() {
                epg.recalculateAndRedraw(true);
            }
        });
        // Do initial load of data.
        new AsyncLoadEPGData(epg).execute();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int[] diff = {0 , 0};
        int step = 100;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_DOWN:
                diff[1] = step;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                diff[1] = -1 * step;
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                diff[0] = step;
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                diff[0] = -1 * step;
                break;

            case 284:
                epg.moveLeft();
                break;
            case 285:
                epg.moveRight();
                break;
            case 286:
                epg.moveUp();
                break;
            case 287:
                epg.moveDown();
                break;
        }
        epg.scrollBy(diff[0], diff[1]);
        return false;
    }


    @Override
    protected void onDestroy() {
        if (epg != null) {
            epg.clearEPGImageCache();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class AsyncLoadEPGData extends AsyncTask<Void, Void, EPGData> {

        EPG epg;

        public AsyncLoadEPGData(EPG epg) {
            this.epg = epg;
        }

        @Override
        protected EPGData doInBackground(Void... voids) {
            return new EPGDataImpl(MockDataService.getMockData());
        }

        @Override
        protected void onPostExecute(EPGData epgData) {
            epg.setEPGData(epgData);
            epg.recalculateAndRedraw(false);
        }
    }
}
