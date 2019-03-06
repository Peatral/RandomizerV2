package com.peatral.randomizerv2;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;

public class Utils {
    public static final int STATE_TAB_ALL = -1; //Don't set it somewhere!!!!
    public static final int STATE_BLACKLIST = 0;
    public static final int STATE_NEUTRAL = 1;
    public static final int STATE_FAV = 2;

    public static String getIDFromName(String name){
        String[] parts = name.toLowerCase().split(" ");
        String id = "";
        for(String s : parts){
            id = id.concat(s);
        }
        id = id.replace("'", "");
        return id;
    }

    public static String getStringResByID(String identifier, Context context){
        return (String) context.getResources().getText(context.getResources().getIdentifier(identifier, "string", context.getPackageName()));
    }

    public static int getMipmapResIdByName(String resName, Context context)  {
        return context.getResources().getIdentifier(resName , "mipmap", context.getPackageName());
    }

    public static ArrayList<Track> getListFromState(ArrayList<Track> list, int state){
        ArrayList<Track> newList = (ArrayList<Track>) list.clone();
        Iterator<Track> iter = newList.iterator();
        while (iter.hasNext()) {
            Track hm = iter.next();
            if (hm.getState() != state) {
                iter.remove();
            }
        }
        return newList;
    }


}
