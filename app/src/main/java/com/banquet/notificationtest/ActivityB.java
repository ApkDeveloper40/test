package com.banquet.notificationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityB extends AppCompatActivity {
    TextView activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityName = findViewById(R.id.tv_activityName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityName.setText("Activity B");
    }
}
