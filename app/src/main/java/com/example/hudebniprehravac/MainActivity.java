package com.example.hudebniprehravac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TableLayout;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tablayout;
    TabItem actual;
    TabItem all;
    TabItem playlist;
    ViewPager viewpager;
    Pager Pager;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        toolbar.setTitle("HUDEBNÍ PŘEHRÁVAČ");

        tablayout=(TabLayout)findViewById(R.id.tabs);
        actual=(TabItem)findViewById(R.id.just_playing);
        all=(TabItem)findViewById(R.id.all_songs);
        playlist=(TabItem)findViewById(R.id.play_list);
        viewpager=(ViewPager)findViewById(R.id.view_pager);


    }

    public class Pager extends FragmentPagerAdapter {
        int tabNum;

        public Pager(FragmentManager fm,int tabNum)
        {
            super(fm);
            this.tabNum=tabNum;
        }

        @Override
        public Fragment getItem(int pos)
        {
            switch (pos)
            {
                case 0: return new JustPlayingFragment();
                case 1: return new AllSongsFragment();
                case 2: return new PlaylistFragment();
                default: break;
            }
            return null;
        }

        @Override
        public int getCount(){
            return tabNum;
        }

    }

}
