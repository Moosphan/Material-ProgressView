package com.moos.progress.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.moos.library.CircleProgressView;
import com.moos.library.HorizontalProgressView;
import com.moos.progress.R;

import static android.content.ContentValues.TAG;

/**
 * A sample of HorizontalProgressView.
 */
public class HorizontalProgressFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener, HorizontalProgressView.HorizontalProgressUpdateListener {


    private AppCompatSeekBar hsb_track_width, hsb_start_progress, hsb_end_progress, hsb_text_size, hsb_corner_radius ;
    private SwitchCompat hsc_trackEnabled, hsc_text_visibility;
    private HorizontalProgressView horizontalProgressView;
    private Button btn_start;
    private TextView textView_call_back;

    public HorizontalProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_horizontal_progress, container, false);
        hsb_track_width = (AppCompatSeekBar) view.findViewById(R.id.hsb_track_width);
        hsb_start_progress = (AppCompatSeekBar) view.findViewById(R.id.hsb_start_progress);
        hsb_end_progress = (AppCompatSeekBar) view.findViewById(R.id.hsb_end_progress);
        hsb_text_size = (AppCompatSeekBar) view.findViewById(R.id.hsb_text_size);
        hsb_corner_radius = (AppCompatSeekBar) view.findViewById(R.id.hsb_corner_radius);
        hsc_trackEnabled = (SwitchCompat) view.findViewById(R.id.hsc_isTracked);
        hsc_text_visibility = (SwitchCompat) view.findViewById(R.id.hsc_text_visibility);
        horizontalProgressView = (HorizontalProgressView) view.findViewById(R.id.progressView_horizontal);
        btn_start = (Button) view.findViewById(R.id.hb_start);
        textView_call_back = (TextView) view.findViewById(R.id.cb_progress_call_back);

        hsb_track_width.setOnSeekBarChangeListener(this);
        hsb_start_progress.setOnSeekBarChangeListener(this);
        hsb_end_progress.setOnSeekBarChangeListener(this);
        hsb_corner_radius.setOnSeekBarChangeListener(this);
        hsb_text_size.setOnSeekBarChangeListener(this);
        hsc_trackEnabled.setOnCheckedChangeListener(this);
        hsc_text_visibility.setOnCheckedChangeListener(this);
        btn_start.setOnClickListener(this);
        horizontalProgressView.setProgressViewUpdateListener(this);

        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.e(TAG, "onProgressChanged: "+progress );
        switch (seekBar.getId()){
            case R.id.hsb_start_progress:
                horizontalProgressView.setStartProgress(progress);
                break;

            case R.id.hsb_end_progress:
                horizontalProgressView.setEndProgress(progress);
                break;

            case R.id.hsb_track_width:
                horizontalProgressView.setTrackWidth((int)(progress*0.5));
                break;

            case R.id.hsb_text_size:
                horizontalProgressView.setProgressTextSize((int)(progress*0.3));
                break;

            case R.id.hsb_corner_radius:
                horizontalProgressView.setProgressCornerRadius((int)(progress*0.5));
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.hsc_isTracked:
                if(isChecked){
                    horizontalProgressView.setTrackEnabled(true);
                }else {
                    horizontalProgressView.setTrackEnabled(false);
                }
                break;

            case R.id.hsc_text_visibility:
                if(isChecked){
                    horizontalProgressView.setProgressTextVisibility(true);
                }else {
                    horizontalProgressView.setProgressTextVisibility(false);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.hb_start){
            horizontalProgressView.startProgressAnimation();
        }
    }

    @Override
    public void onProgressUpdate(float progress) {
        textView_call_back.setText("progress: "+ (int) (progress)+"%");
    }
}
