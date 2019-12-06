package com.example.hudebniprehravac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TableLayout;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.tabs.TabItem;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TableLayout tablayout;
    TabItem actual;
    TabItem all;
    TabItem playlist;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        toolbar.setTitle("HUDEBNÍ PŘEHRÁVAČ");

        tablayout=(TableLayout)findViewById(R.id.tabs);
        actual=(TabItem)findViewById(R.id.just_playing);
        all=(TabItem)findViewById(R.id.all_songs);
        playlist=(TabItem)findViewById(R.id.play_list);

    }

}
