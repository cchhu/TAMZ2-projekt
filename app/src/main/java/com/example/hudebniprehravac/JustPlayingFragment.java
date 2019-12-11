package com.example.hudebniprehravac;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class JustPlayingFragment extends Fragment {


    public JustPlayingFragment() {
        // Required empty public constructor
    }
    TextView title;
    ImageView playbutt;
    ImageView musfoto;
    GifImageView visual;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_just_playing, container, false);
        super.onViewCreated(view, savedInstanceState);

        title = view.findViewById(R.id.songgtitle);
        visual=view.findViewById(R.id.visualizer);
        musfoto=view.findViewById(R.id.imageView3);
        playbutt=view.findViewById(R.id.imageView);
        if(Global.titname!="")
        {
            title.setText(Global.titname);
            musfoto.setImageAlpha(55);
        }

        if(Global.media==null){
            title.setText("NO MUSIC PLAYS!");
            visual.setVisibility(View.GONE);
            playbutt.setVisibility(View.GONE);
            title.setTypeface(null, Typeface.BOLD);
        }

        if(Global.media!=null) {
            if (Global.media.isPlaying() == false) {
                playbutt.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                playbutt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Global.media.start();
                        refresh();
                    }
                });
                visual.setVisibility(View.GONE);
            }

            if (Global.media.isPlaying()) {
                playbutt.setImageResource(R.drawable.ic_pause_black_24dp);
                playbutt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Global.media.pause();
                        refresh();
                    }
                });
                Global.media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        title.setText("NO MUSIC PLAYS!");
                        visual.setVisibility(View.GONE);
                        playbutt.setVisibility(View.GONE);
                        title.setTypeface(null, Typeface.BOLD);
                    }
                });

                visual.setVisibility(View.VISIBLE);
            }
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

    public void refresh()
    {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

}