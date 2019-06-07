package com.banquet.notificationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityA extends AppCompatActivity {
    TextView activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent() != null && getIntent().hasExtra("tag"))
            Toast.makeText(ActivityA.this,getIntent().getStringExtra("tag"),Toast.LENGTH_SHORT).show();

        activityName = findViewById(R.id.tv_activityName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityName.setText("Activity A");
    }
}
