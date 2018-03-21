package com.moos.progress;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.moos.library.CircleProgressView;
import com.moos.progress.fragment.CircleProgressFragment;
import com.moos.progress.fragment.HorizontalProgressFragment;

/**
 * by Moos on 2018/03/21
 * just a example to use it
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SwitchCompat switcher;
    private FrameLayout container;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switcher = (SwitchCompat) findViewById(R.id.fragment_switch);
        container = (FrameLayout) findViewById(R.id.fragment_container);
        title = (TextView) findViewById(R.id.fragment_title);
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


}
