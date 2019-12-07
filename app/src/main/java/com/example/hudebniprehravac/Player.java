package com.example.hudebniprehravac;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends AppCompatActivity {

    MediaPlayer media;
    Bundle songExtraData;
    ArrayList<File> songFileList;
    SeekBar seek;
    TextView title;
    ImageView playbutt;
    ImageView nextbutt;
    ImageView prevbutt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player);

        seek = findViewById(R.id.seekBar2);
        title = findViewById(R.id.songtitle);
        playbutt = findViewById(R.id.imageView6);
        nextbutt = findViewById(R.id.imageView7);
        prevbutt = findViewById(R.id.imageView8);

        if(media!=null){
            media.stop();
        }
        Intent songData = getIntent();
        songExtraData = songData.getExtras();
        songFileList = (ArrayList)songExtraData.getParcelableArrayList("songFileList");
        int position = songExtraData.getInt("position", 0);
        runMusic(position);

        playbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

    }

    private void runMusic(final int position) {
        if (media != null && media.isPlaying()) {
            media.reset();
        }

        String name = songFileList.get(position).getName();
        title.setText(name);
        Uri songResourdce = Uri.parse(songFileList.get(position).toString());
        media = MediaPlayer.create(getApplicationContext(), songResourdce);

        media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seek.setMax(media.getDuration());
                media.start();
                playbutt.setImageResource(R.drawable.ic_pause_black_24dp);
            }
        });

        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                runMusic(position+1);
            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                {
                    media.seekTo(progress);
                    seek.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        @SuppressLint("HandlerLeak") final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg){
                seek.setProgress(msg.what);
            }
        };

        new Thread(new Runnable(){
            @Override
            public void run(){
                while(media!=null)
                    try{
                        if(media.isPlaying())
                        {
                            Message message=new Message();
                            message.what = media.getCurrentPosition();
                            handler.sendMessage(message);
                            Thread.sleep(5);
                        }}catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
            }
        }).start();


    }

    private void play(){
        if(media!=null && media.isPlaying())
        {
            media.pause();
            playbutt.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
        else {
            media.start();
            playbutt.setImageResource(R.drawable.ic_pause_black_24dp);
        }
    }
}

