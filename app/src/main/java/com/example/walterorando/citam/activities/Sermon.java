package com.example.walterorando.citam.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.walterorando.citam.R;

import static com.example.walterorando.citam.R.id.frame;

/**
 * Created by Walter Orando on 4/13/2017.
 */

public class Sermon extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sermon_activity);
      //  getLayoutInflater().inflate(R.layout.sermon_activity, frame);
        setTitle("Sermons");
    }
}
