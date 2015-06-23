package com.gabitosoft.bluetoothmeasure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * Created by Gabriel Delgado on 29-05-15.
 */
public class FragmentGraphic extends Fragment{

    private SeekBar seekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try {

            View rootView = inflater.inflate(R.layout.graphic_layout, container, false);
            seekBar = (SeekBar) getActivity().findViewById(R.id.seekBar);

            return rootView;
        } catch (Exception ex) {

            return null;
        }
    }
}
