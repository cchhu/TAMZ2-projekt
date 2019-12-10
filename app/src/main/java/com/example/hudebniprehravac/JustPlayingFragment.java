package com.example.hudebniprehravac;


import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class JustPlayingFragment extends Fragment {


    public JustPlayingFragment() {
        // Required empty public constructor
    }
    Bundle songExtraData;
    ArrayList<File> songFileList;
    SeekBar seek;
    TextView title;
    ImageView playbutt;
    ImageView nextbutt;
    ImageView prevbutt;
    TextView currtime;
    TextView endtime;
    ImageView repeat;
    ImageView musfoto;
    boolean repeatsong=false;
    int currensong;
    int position;
    ImageView random;
    boolean randomsongs=false;
    int x=0;
    GifImageView visual;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_just_playing, container, false);
        super.onViewCreated(view, savedInstanceState);


        title = view.findViewById(R.id.songgtitle);
        visual=view.findViewById(R.id.visualizer);
        musfoto=view.findViewById(R.id.imageView3);
        playbutt=view.findViewById(R.id.imageView);
        if(Global.media==null){
            title.setText("NO MUSIC PLAYS!");
            visual.setVisibility(View.GONE);
          //  media.stop();
        }

        if(Global.titname!="")
        {
            title.setText(Global.titname);
            musfoto.setImageAlpha(55);
            visual.setVisibility(View.VISIBLE);
        }

        if(Global.media!=null && Global.media.isPlaying())
        {
                playbutt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Global.media.pause();
                    }
                });
        }
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();

        }
    }



}

