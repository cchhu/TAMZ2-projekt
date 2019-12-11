package com.example.hudebniprehravac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tablayout;
    TabItem actual;
    TabItem all;
    ViewPager viewpager;
    Pager Pager;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("HUDEBNÍ PŘEHRÁVAČ");

        tablayout=findViewById(R.id.tabs);
        actual=findViewById(R.id.just_playing);
        all=findViewById(R.id.all_songs);
        viewpager=findViewById(R.id.view_pager);

        Pager=new Pager(getSupportFragmentManager(),tablayout.getTabCount());
        viewpager.setAdapter(Pager);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
            viewpager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {}
            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {}

        });
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
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
