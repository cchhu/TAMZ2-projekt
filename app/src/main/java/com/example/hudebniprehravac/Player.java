package com.example.hudebniprehravac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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

        Intent songData = getIntent();
        songExtraData = songData.getExtras();
        songFileList = (ArrayList)songExtraData.getParcelableArrayList("songFileList");
        int position = songExtraData.getInt("position", 0);
        runMusic(position);

    }

    private void runMusic(int position) {
        if (media != null && media.isPlaying()) {
            media.stop();
        }

        String name = songFileList.get(position).getName();
        title.setText(name);
        Uri songResourdce = Uri.parse(songFileList.get(position).toString());
        media = media.create(getApplicationContext(), songResourdce);

        media.start();

        if (media.isPlaying()) {
            playbutt.setImageResource(R.drawable.ic_pause_black_24dp);
        } else {
            playbutt.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
    }
}
