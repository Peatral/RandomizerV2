package com.peatral.randomizerv2;

import android.content.SharedPreferences;

import java.util.ArrayList;

public class SingletonClass {
    private  static  SingletonClass instance;

    private ArrayList<Track> tracks;

    public SingletonClass() {
    }

    public static SingletonClass getInstance() {
        if(instance == null){
            instance = new SingletonClass();
        }
        return instance;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public Track getTrackFromID(String id){
        for(Track track : tracks){
            if(track.getID().equals(id)){
                return track;
            }
        }
        return tracks.get(0);
    }
}
