package com.moos.progress.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.moos.library.HorizontalProgressView;
import com.moos.progress.R;

import java.util.ArrayList;
import java.util.List;

public class ProgressListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.progress_list_recyclerView);
        ImageView backButton = (ImageView) findViewById(R.id.progress_list_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ProgressListAdapter(this, generateData()));

    }

    private ArrayList<ProgressInfo> generateData() {
        ArrayList<ProgressInfo> data = new ArrayList<>();
        data.add(new ProgressInfo(0, 60, R.color.blue_start, R.color.purple_start, false));
        data.add(new ProgressInfo(20, 80, R.color.purple_start, R.color.red_end, true));
        data.add(new ProgressInfo(0, 100, R.color.dark_orange, R.color.blue_end, true));
        data.add(new ProgressInfo(32, 50, R.color.blue_start, R.color.red_start, false));
        data.add(new ProgressInfo(10, 70, R.color.green_start, R.color.purple_start, true));
        data.add(new ProgressInfo(12, 88, R.color.purple_end, R.color.green_end, false));
        data.add(new ProgressInfo(60, 90, R.color.blue_end, R.color.purple_light, true));
        data.add(new ProgressInfo(0, 100, R.color.dark_orange, R.color.blue_end, true));
        data.add(new ProgressInfo(32, 50, R.color.blue_start, R.color.red_start, false));
        data.add(new ProgressInfo(10, 70, R.color.green_start, R.color.purple_start, true));
        data.add(new ProgressInfo(12, 88, R.color.purple_end, R.color.green_end, false));
        data.add(new ProgressInfo(60, 90, R.color.blue_end, R.color.purple_light, true));
        data.add(new ProgressInfo(0, 100, R.color.dark_orange, R.color.blue_end, true));
        data.add(new ProgressInfo(32, 50, R.color.blue_start, R.color.red_start, false));
        data.add(new ProgressInfo(10, 70, R.color.green_start, R.color.purple_start, true));
        data.add(new ProgressInfo(12, 88, R.color.purple_end, R.color.green_end, false));
        data.add(new ProgressInfo(60, 90, R.color.blue_end, R.color.purple_light, true));
        return data;
    }

    private class ProgressListAdapter extends RecyclerView.Adapter<ProgressListAdapter.ProgressViewHolder>{
        private Context ctx;
        private ArrayList<ProgressInfo> progressList;

        ProgressListAdapter(Context context, ArrayList<ProgressInfo> data){
            this.ctx = context;
            this.progressList = data;
        }

        @Override
        public ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.recycler_item_layout, null);
            return new ProgressViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ProgressViewHolder holder, int position) {
            int pos = holder.getLayoutPosition();
            holder.progressView.setStartProgress(progressList.get(pos).fromValue);
            holder.progressView.setEndProgress(progressList.get(pos).toValue);
            holder.progressView.setStartColor(ctx.getResources().getColor(progressList.get(pos).startColor));
            holder.progressView.setEndColor(ctx.getResources().getColor(progressList.get(pos).endColor));
            holder.progressView.setTrackEnabled(progressList.get(pos).trackable);
            holder.progressView.setProgressDuration(5000);
            holder.startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.progressView.startProgressAnimation();
                }
            });
        }

        @Override
        public int getItemCount() {
            return progressList.size();
        }

        class ProgressViewHolder extends RecyclerView.ViewHolder{
            HorizontalProgressView progressView;
            Button startButton;
            public ProgressViewHolder(View itemView) {
                super(itemView);
                progressView = (HorizontalProgressView) itemView.findViewById(R.id.recycler_item_progressView);
                startButton = (Button) itemView.findViewById(R.id.recycler_item_start_btn);
            }
        }
    }

    class ProgressInfo {
        private float fromValue;
        private float toValue;
        private int startColor;
        private int endColor;
        private boolean trackable;

        public ProgressInfo(float fromValue, float toValue, int startColor, int endColor, boolean trackable) {
            this.fromValue = fromValue;
            this.toValue = toValue;
            this.startColor = startColor;
            this.endColor = endColor;
            this.trackable = trackable;
        }

        public float getFromValue() {
            return fromValue;
        }

        public void setFromValue(float fromValue) {
            this.fromValue = fromValue;
        }

        public float getToValue() {
            return toValue;
        }

        public void setToValue(float toValue) {
            this.toValue = toValue;
        }

        public int getStartColor() {
            return startColor;
        }

        public void setStartColor(int startColor) {
            this.startColor = startColor;
        }

        public int getEndColor() {
            return endColor;
        }

        public void setEndColor(int endColor) {
            this.endColor = endColor;
        }

        public boolean isTrackable() {
            return trackable;
        }

        public void setTrackable(boolean trackable) {
            this.trackable = trackable;
        }
    }
}

