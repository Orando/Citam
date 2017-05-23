package com.example.walterorando.citam.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.walterorando.citam.R;
import com.example.walterorando.citam.activities.MainActivity;
import com.example.walterorando.citam.activities.Sermon;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Walter Orando on 3/29/2017.
 */

public class Home_Fragment extends Fragment implements View.OnClickListener {
    ImageSwitcher iSwitcher;
    Integer SWITCH_MS = 4500;
    Integer imgCount =0;
    Integer[] imgArr;
    Button btnNews, btnSermon;
    public Home_Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container, false);
        iSwitcher = (ImageSwitcher)v.findViewById(R.id.imageSlider);
        iSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getActivity());
                myView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                myView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                return myView;
            }
        });

        Animation in = AnimationUtils.loadAnimation(getActivity(),android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getActivity(),android.R.anim.slide_out_right);
        iSwitcher.setInAnimation(in);
        iSwitcher.setOutAnimation(out);

        imgArr = new Integer[3];
        imgArr[0]=(R.drawable.background);
        imgArr[1]=(R.drawable.background);
        imgArr[2]=(R.drawable.background);

        imageSwitch();

        btnNews = (Button) v.findViewById(R.id.btnNews);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnSermon = (Button) v.findViewById(R.id.btnSermons);
        btnSermon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Sermon.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return v;
    }

    private void imageSwitch(){
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        iSwitcher.setImageResource(imgArr[imgCount]);
                        imgCount++;
                        if(imgCount >= imgArr.length){
                            imgCount = 0;
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, SWITCH_MS); //execute in every 10000 ms

    }

    @Override
    public void onClick(View view) {

    }

    private class switchImage extends AsyncTask<Integer, Void, Void> {
        protected Void doInBackground(final Integer... num) {

            return null;
        }
    }

}
