package com.peatral.randomizerv2;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private ArrayList<Track> list;

    private int state = Utils.STATE_TAB_ALL;

    public GridView grid;

    public ListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        grid = view.findViewById(R.id.gridView);

        Bundle b = getArguments();
        if(b != null){
            state = b.getInt("tab");
        }

        reload();

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String trackID = Utils.getIDFromName(((TextView)view.findViewById(R.id.textView_name)).getText().toString());

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                Bundle b = new Bundle();
                b.putString("trackID", trackID);
                bottomSheetDialog.setArguments(b);
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "bottomSheet");

                //Toast.makeText(getContext(), "" + item, Toast.LENGTH_SHORT).show();

            }
        });

        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = ((TextView)view.findViewById(R.id.textView_name)).getText().toString();
                Snackbar.make(view, item, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });

        return view;
    }

    public void reload(){
        list = SingletonClass.getInstance().getTracks();
        if (state != Utils.STATE_TAB_ALL) {
            list = Utils.getListFromState(list, state);
        }
        Collections.sort(list);


        GridViewAdapter adapter = new GridViewAdapter(list, getContext());
        grid.setAdapter(adapter);
    }
}
