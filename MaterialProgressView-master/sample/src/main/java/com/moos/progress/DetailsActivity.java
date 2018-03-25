package com.moos.progress;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.moos.progress.fragment.CircleProgressFragment;
import com.moos.progress.fragment.HorizontalProgressFragment;

/**
 * created by Moos on 2018/03/23
 * desc: some details of two styles of material-progressView
 */
public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private SwitchCompat switcher;
    private FrameLayout container;
    private TextView title;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        switcher = (SwitchCompat) findViewById(R.id.fragment_switch);
        container = (FrameLayout) findViewById(R.id.fragment_container);
        title = (TextView) findViewById(R.id.fragment_title);
        iv_back = (ImageView) findViewById(R.id.detail_back_btn);
        iv_back.setOnClickListener(this);
        replaceFragment(new CircleProgressFragment());
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    title.setText(getString(R.string.fragment_name_horizontal_progress));
                    replaceFragment(new HorizontalProgressFragment());
                }else {
                    title.setText(getString(R.string.fragment_name_circle_progress));
                    replaceFragment(new CircleProgressFragment());
                }
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.detail_back_btn){
            finish();
        }
    }
}
