package com.peatral.randomizerv2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    private Track track;
    private BottomSheetListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        Bundle b = getArguments();
        String trackID = "";
        if(b != null){
            trackID = b.getString("trackID");
        }

        track = SingletonClass.getInstance().getTrackFromID(trackID);

        ImageView imageViewTrackIcon = view.findViewById(R.id.imageView_trackIcon);
        ImageView imageViewCupIcon = view.findViewById(R.id.imageView_cupIcon);

        TextView textViewTrackName = view.findViewById(R.id.textView_trackNameHeading);
        TextView textViewCupName = view.findViewById(R.id.textView_contentCupName);
        TextView textViewType = view.findViewById(R.id.textView_contentType);

        final TextView textViewState = view.findViewById(R.id.textView_contentState);

        SeekBar seekBarState = view.findViewById(R.id.seekBar_state);

        final Button buttonOk = view.findViewById(R.id.button_ok);


        imageViewTrackIcon.setImageResource(Utils.getMipmapResIdByName("track_" + track.getID(), getContext()));
        imageViewCupIcon.setImageResource(Utils.getMipmapResIdByName("cup_" + track.getCup(), getContext()));

        textViewTrackName.setText(Utils.getStringResByID("track_" + track.getID(), getContext()));
        textViewCupName.setText(Utils.getStringResByID("cup_" + track.getCup(), getContext()));
        textViewType.setText(Utils.getStringResByID("type_" + track.getType(), getContext()));

        textViewState.setText(getText(R.string.state) + ": " + Utils.getStringResByID("state_" + track.getState(), getContext()));
        seekBarState.setProgress(track.getState());
        seekBarState.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = track.getState();

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                textViewState.setText(getText(R.string.state) + ": " + Utils.getStringResByID("state_" + i, getContext()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                track.setState(progress);
                try{
                    ((MainActivity)getActivity()).saveData();
                }
                catch (ClassCastException e){
                    throw new ClassCastException(e.getMessage());
                }
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onButtonClicked(buttonOk.getId());
                dismiss();
            }
        });

        return view;
    }

    public interface BottomSheetListener {
        void onButtonClicked(int id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement BottomSheetListener");
        }
    }
}
