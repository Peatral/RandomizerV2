package com.peatral.randomizerv2;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

public class Track implements Comparable<Track>{
    private String id;
    private String cup;
    private String type;
    private int state;

    public Track(String id, String cup, String type, int state)  {
        this.id = id;
        this.cup = cup;
        this.type = type;
        this.state = state;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int compareTo(@NonNull Track track) {
        ArrayList<String> s = new ArrayList<>();
        s.add(getID());
        s.add(track.getID());
        Collections.sort(s);
        if(s.indexOf(getID()) == 0) {
            return -1;
        }
        else{
            return 1;
        }
    }
}
