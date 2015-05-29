package com.gabitosoft.bluetoothmeasure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gabriel Delgado on 29-05-15.
 */
public class FragmentGraphic extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.configuration_layout, container, false);
        return rootView;
    }
}
