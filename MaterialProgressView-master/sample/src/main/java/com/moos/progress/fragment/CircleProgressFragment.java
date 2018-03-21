package com.moos.progress.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.moos.library.CircleProgressView;
import com.moos.progress.R;

/**
 * A sample of CircleProgressView.
 */
public class CircleProgressFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener, CircleProgressView.CircleProgressUpdateListener {

    private AppCompatSeekBar csb_track_width, csb_start_progress, csb_end_progress, csb_text_size;
    private SwitchCompat csc_trackEnabled, csc_fillEnabled, csc_circleBroken;
    private CircleProgressView circleProgressView;
    private Button btn_start, btn_call_back;

    public CircleProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_circle_progress, container, false);
        csb_track_width = (AppCompatSeekBar) view.findViewById(R.id.csb_track_width);
        csb_start_progress = (AppCompatSeekBar) view.findViewById(R.id.csb_start_progress);
        csb_end_progress = (AppCompatSeekBar) view.findViewById(R.id.csb_end_progress);
        csb_text_size = (AppCompatSeekBar) view.findViewById(R.id.csb_text_size);
        csc_trackEnabled = (SwitchCompat) view.findViewById(R.id.csc_isTracked);
        csc_fillEnabled = (SwitchCompat) view.findViewById(R.id.csc_isFilled);
        csc_circleBroken = (SwitchCompat) view.findViewById(R.id.csc_circleBroken);
        circleProgressView = (CircleProgressView) view.findViewById(R.id.progressView_circle);
        btn_start = (Button) view.findViewById(R.id.cb_start);
        btn_call_back = (Button) view.findViewById(R.id.cb_progress_call_back);
        csb_track_width.setOnSeekBarChangeListener(this);
        csb_start_progress.setOnSeekBarChangeListener(this);
        csb_end_progress.setOnSeekBarChangeListener(this);
        csb_text_size.setOnSeekBarChangeListener(this);
        csc_trackEnabled.setOnCheckedChangeListener(this);
        csc_circleBroken.setOnCheckedChangeListener(this);
        csc_fillEnabled.setOnCheckedChangeListener(this);
        btn_start.setOnClickListener(this);
        btn_call_back.setOnClickListener(this);
        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.csb_track_width:
                circleProgressView.setTrackWidth(progress);
                break;

            case R.id.csb_start_progress:
                circleProgressView.setStartProgress(progress);
                break;

            case R.id.csb_end_progress:
                circleProgressView.setEndProgress(progress);
                break;

            case R.id.csb_text_size:
                circleProgressView.setProgressTextSize((int) (progress*0.5));
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
            case R.id.csc_isTracked:
                if (isChecked){
                    circleProgressView.setTrackEnabled(true);
                }else {
                    circleProgressView.setTrackEnabled(false);
                }

                break;

            case R.id.csc_circleBroken:
                if (isChecked){
                    circleProgressView.setCircleBroken(true);
                }else {
                    circleProgressView.setCircleBroken(false);
                }
                break;

            case R.id.csc_isFilled:
                if (isChecked){
                    circleProgressView.setFillEnabled(true);
                }else {
                    circleProgressView.setFillEnabled(false);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cb_start){
            circleProgressView.startProgressAnimation();
        }else if(v.getId() == R.id.cb_progress_call_back){
            circleProgressView.setProgressViewUpdateListener(this);
            circleProgressView.startProgressAnimation();
        }
    }

    @Override
    public void onProgressUpdate(float progress) {
        btn_call_back.setText("progress: "+ (int) (progress)+"%");
    }
}
