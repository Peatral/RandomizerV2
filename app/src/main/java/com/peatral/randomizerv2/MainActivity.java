package com.peatral.randomizerv2;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements BottomSheetDialog.BottomSheetListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private ArrayList<Track> tracks = new ArrayList<Track>();

    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        for (Tracks track : Tracks.values()) {
            Track track1 = new Track(track.getName(), track.getCup(), track.getType(), Utils.STATE_NEUTRAL);
            tracks.add(track1);
        }
        SingletonClass.getInstance().setTracks(tracks);
        loadData();

        FloatingActionButton fab = findViewById(R.id.fab_getRandomTrack);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Track> tracks = (ArrayList<Track>) SingletonClass.getInstance().getTracks().clone();
                for (Track t : Utils.getListFromState(tracks, Utils.STATE_BLACKLIST)){
                    tracks.remove(t);
                }
                if(tracks.size() > 0) {
                    Track rand = tracks.get(new Random().nextInt(tracks.size()));

                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                    Bundle b = new Bundle();
                    b.putString("trackID", rand.getID());
                    bottomSheetDialog.setArguments(b);
                    bottomSheetDialog.show(getSupportFragmentManager(), "bottomSheet");
                }
                else{
                    Snackbar.make(view, "No Tracks to pick from", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(view, "Will pick a random Track", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String s = dumpTrackListToJSONString();
        Log.i("SAVEDATA", s);
        editor.putString("trackList", s);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String s = sharedPreferences.getString("trackList", dumpTrackListToJSONString());
        Log.i("LOADDATA", s);
        parseTrackListFromJSONString(s);
    }

    public String dumpTrackListToJSONString(){
        JSONObject list = new JSONObject();
        for(Track t : SingletonClass.getInstance().getTracks()){
            try {
                list.put(t.getID(), t.getState());
            }
            catch(Exception e){
                Log.e("DumpList", e.getMessage());
            }
        }
        return list.toString();
    }

    public void parseTrackListFromJSONString(String s){

        try {
            JSONObject list = new JSONObject(s);
            ArrayList<Track> newList = new ArrayList<>();
            for(Track t : SingletonClass.getInstance().getTracks()){
                t.setState(list.getInt(t.getID()));
                newList.add(t);
            }
            SingletonClass.getInstance().setTracks(newList);
        }
        catch (Exception e){
            Log.e("ParseList", e.getMessage());
        }

    }

    @Override
    public void onButtonClicked(int id) {
        TabLayout tabs = findViewById(R.id.tabs);

        mViewPager.getAdapter().notifyDataSetChanged();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ListFragment lf = new ListFragment();
            Bundle b = new Bundle();
            int state = 1;
            switch (position){
                case 1:
                    state = Utils.STATE_BLACKLIST;
                    break;
                case 2:
                    state = Utils.STATE_NEUTRAL;
                    break;
                case 3:
                    state = Utils.STATE_FAV;
                    break;
                default:
                    state = Utils.STATE_TAB_ALL;
            }
            b.putInt("tab", state);
            lf.setArguments(b);
            return lf;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }
}
