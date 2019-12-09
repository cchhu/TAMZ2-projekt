package com.example.hudebniprehravac;


import android.media.MediaPlayer;
import android.os.Bundle;

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

        View view = inflater.inflate(R.layout.fragment_just_playing, container, false);
        super.onViewCreated(view, savedInstanceState);


        title = view.findViewById(R.id.songgtitle);

        if(media==null){
            title.setText("NO MUSIC PLAYS!");

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

