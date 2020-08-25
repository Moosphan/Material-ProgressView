package com.moos.progress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.moos.library.CircleProgressView;
import com.moos.library.HorizontalProgressView;
import com.moos.progress.R;

/**
 * by Moos on 2018/03/21
 * just a design example to use it
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, HorizontalProgressView.HorizontalProgressUpdateListener, CircleProgressView.CircleProgressUpdateListener {
    private static final String TAG = "MainActivity";
    private CircleProgressView cpv_main, cpv_small;
    private HorizontalProgressView hpv_language, hpv_math, hpv_history, hpv_english;
    private TextView tv_language, tv_math, tv_history, tv_english, tv_main;
    private Button button;
    private ImageView bt_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        cpv_main = (CircleProgressView) findViewById(R.id.progressView_circle_main);
        cpv_small = (CircleProgressView) findViewById(R.id.progressView_circle_small);
        hpv_language = (HorizontalProgressView) findViewById(R.id.hpv_language);
        hpv_math = (HorizontalProgressView) findViewById(R.id.hpv_math);
        hpv_history = (HorizontalProgressView) findViewById(R.id.hpv_history);
        hpv_english = (HorizontalProgressView) findViewById(R.id.hpv_english);
        tv_language = (TextView) findViewById(R.id.progress_text_language);
        tv_english = (TextView) findViewById(R.id.progress_text_english);
        tv_history = (TextView) findViewById(R.id.progress_text_history);
        tv_math = (TextView) findViewById(R.id.progress_text_math);
        tv_main = (TextView) findViewById(R.id.progress_text_main);
        button = (Button) findViewById(R.id.btn_start);
        bt_detail = (ImageView) findViewById(R.id.btn_details);
        button.setOnClickListener(this);
        bt_detail.setOnClickListener(this);
        initProgressViews();
    }

    private void initProgressViews(){
        hpv_language.setProgressViewUpdateListener(this);
        hpv_english.setProgressViewUpdateListener(this);
        hpv_history.setProgressViewUpdateListener(this);
        hpv_math.setProgressViewUpdateListener(this);
        cpv_small.setProgressViewUpdateListener(this);

        cpv_main.setGraduatedEnabled(true);
        cpv_main.setProgressViewUpdateListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_start){
            //cpv_small.startProgressAnimation();
            //cpv_main.startProgressAnimation();
            hpv_language.startProgressAnimation();
            hpv_math.startProgressAnimation();
            hpv_history.startProgressAnimation();
            hpv_english.startProgressAnimation();
        }else if(v.getId() == R.id.btn_details){
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onHorizontalProgressStart(View view) {

    }

    @Override
    public void onHorizontalProgressUpdate(View view, float progress) {
        int progressInt = (int) progress;
        switch (view.getId()){
            case R.id.hpv_language:
                tv_language.setText(progressInt + "%");
                break;

            case R.id.hpv_english:
                tv_english.setText(progressInt + "%");
                break;

            case R.id.hpv_history:
                tv_history.setText(progressInt + "%");
                break;

            case R.id.hpv_math:
                tv_math.setText(progressInt + "%");
                break;

        }
    }

    @Override
    public void onHorizontalProgressFinished(View view) {

        if(view.getId() == R.id.hpv_language){
            cpv_small.startProgressAnimation();
        }
    }

    @Override
    public void onCircleProgressStart(View view) {

    }

    @Override
    public void onCircleProgressUpdate(View view, float progress) {

        int progressInt = (int) progress;
        if(view.getId() == R.id.progressView_circle_main){
            tv_main.setText(progressInt+"");
        }
    }

    @Override
    public void onCircleProgressFinished(View view) {

        if(view.getId() == R.id.progressView_circle_small){

            cpv_main.startProgressAnimation();
        }

    }
}
