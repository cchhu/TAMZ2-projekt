package com.example.hudebniprehravac;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JustPlayingFragment extends Fragment {


    public JustPlayingFragment() {
        // Required empty public constructor
    }
    static MediaPlayer media;
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
    boolean repeatsong=false;
    int currensong;
    int position;
    ImageView random;
    boolean randomsongs=false;
    int x=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_playercopy, container, false);
        super.onViewCreated(view, savedInstanceState);


        seek = (SeekBar)view.findViewById(R.id.seekBar2);
        title = view.findViewById(R.id.songgtitle);
        playbutt = view.findViewById(R.id.imageView6);
        nextbutt = view.findViewById(R.id.imageView7);
        prevbutt = view.findViewById(R.id.imageView8);
        currtime=view.findViewById(R.id.durstart);
        endtime=view.findViewById(R.id.durend);
        repeat=view.findViewById(R.id.rep);
        random=view.findViewById(R.id.random);

        if(media==null){
            title.setText("NO MUSIC PLAYS!");
            currtime.setVisibility(View.GONE);
            endtime.setVisibility(View.GONE);

          //  media.stop();
        }

        if(Global.titname!="")
        {
            title.setText(Global.titname);
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

