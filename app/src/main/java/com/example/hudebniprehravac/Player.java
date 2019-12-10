package com.example.hudebniprehravac;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class Player extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        seek = findViewById(R.id.seekBar2);
        title = findViewById(R.id.songgtitle);
        playbutt = findViewById(R.id.imageView6);
        nextbutt = findViewById(R.id.imageView7);
        prevbutt = findViewById(R.id.imageView8);
        currtime=findViewById(R.id.durstart);
        endtime=findViewById(R.id.durend);
        repeat=findViewById(R.id.rep);
        random=findViewById(R.id.random);

        if(media!=null){
            media.stop();
        }
        Intent songData = getIntent();
        songExtraData = songData.getExtras();
        songFileList = (ArrayList)songExtraData.getParcelableArrayList("songFileList");
        position = songExtraData.getInt("position", 0);

        runMusic(position);

        playbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });


        nextbutt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (position < songFileList.size() - 1) {
            position++;
        } else {
            position = 0;
        }
        if(repeatsong==true && randomsongs==false)
        {
            currensong=position;
            runMusic(currensong);
        }
        if(repeatsong==false && randomsongs==false) {
            runMusic(position);
        }

        if(randomsongs==true && repeatsong==true)
        {
            x = (int)(Math.random()*((songFileList.size()-1)+1))+1;
            currensong=x;
            runMusic(currensong);
        }

        if(randomsongs==true && repeatsong==false)
        {
            x = (int)(Math.random()*((songFileList.size()-1)+1))+1;
            currensong=x;
            runMusic(currensong);
        }

      //  else { runMusic(position);}
    }

});
    prevbutt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(position<=0){
                position=songFileList.size();

            }
            else{
                position--;

            }
        if(repeatsong==true && randomsongs==false)
        {
            currensong=position;
            runMusic(currensong);
        }
        if(repeatsong==false && randomsongs==false) {
            runMusic(position);
        }

        if(randomsongs==true && repeatsong==true)
        {
            x = (int)(Math.random()*((songFileList.size()-1)+1))+1;
            currensong=x;
            runMusic(currensong);
        }

        if(randomsongs==true && repeatsong==false)
        {
            x = (int)(Math.random()*((songFileList.size()-1)+1))+1;
            currensong=x;
            runMusic(currensong);
        }

      //  else { runMusic(position);}

        }



    });

    repeat.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            currensong = position;

            if(repeatsong==true)
            {
                repeatsong=false;
                repeat.setImageResource(R.drawable.ic_repeat_black_24dp);
            }
            else {
                repeatsong = true;
                repeat.setImageResource(R.drawable.ic_repeat_green_24dp);
            }

        }
    });

    random.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(randomsongs==true)
            {
               randomsongs=false;
                random.setImageResource(R.drawable.ic_shuffle_black_24dp);
            }
            else {
                randomsongs=true;
                random.setImageResource(R.drawable.ic_shuffle_green_24dp);
            }
        }
    });



    }
/*
    @Override
    public void onBackPressed(){
       // media.stop();
        JustPlayingFragment fragment_obj = (JustPlayingFragment) getSupportFragmentManager().findFragmentById(R.id.songtitle);
        ((TextView) fragment_obj.getView().findViewById(R.id.songtitle)).setText("test");

     //   finish();
      //  setContentView(R.layout.fragment_all_songs);
    }*/


    private void runMusic(final int position) {

        if (media != null && media.isPlaying()) {
            media.reset();
        }

        String name = songFileList.get(position).getName();
        title.setText(name);
        Global.titname=name;
        Uri songResourdce = Uri.parse(songFileList.get(position).toString());
        media = MediaPlayer.create(getApplicationContext(), songResourdce);
        Global.media=media;
        media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seek.setMax(media.getDuration());


                String tt=createTimer(media.getDuration());
                endtime.setText(tt);
                Global.endduration=tt;
                media.start();
                playbutt.setImageResource(R.drawable.ic_pause_black_24dp);
            }
        });

        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
        //        runMusic(position+1);

                playbutt.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                Global.titname="";

                if(repeatsong==true && randomsongs==false)
                {
                    runMusic(currensong);
                }

                if(randomsongs==true && repeatsong==false)
                {
                    x = (int)(Math.random()*((songFileList.size()-1)+1))+1;
                    runMusic(x);
                }

                if(randomsongs==true && repeatsong==true)
                {
                    runMusic(currensong);
                }

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
                int curpos=msg.what;
                currtime.setText(createTimer(curpos));
                Global.actualduration=(createTimer(curpos));
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
                            Thread.sleep(2);
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

    public String createTimer(int duration){
        String timerLabel="";
        int min=duration/1000/60;
        int sec=duration/1000%60;

        timerLabel=timerLabel+min+":";
        if(sec<10)timerLabel=timerLabel+"0";
        timerLabel=timerLabel+sec;
        return timerLabel;
    }

}

