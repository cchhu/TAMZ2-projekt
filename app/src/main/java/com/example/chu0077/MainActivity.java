package com.example.chu0077;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        toolbar.setTitle("HUDEBNÍ PŘEHRÁVAČ");
       // setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.FontChange);


        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.view_pager);

        setUpViewPaper(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPaper(ViewPager viewPager) {
        FragmentAdapter adapter=new FragmentAdapter(getSupportFragmentManager());
        adapter.AddFragments(new Song(),"Songs");
        adapter.AddFragments(new Artist(),"Artist");
        adapter.AddFragments(new Album(),"Album");
        viewPager.setAdapter(adapter);
    }

    private class FragmentAdapter extends FragmentPagerAdapter{

        private List<Fragment> fragmentList=new ArrayList<>();
        private List<String> titleList=new ArrayList<>();

        public FragmentAdapter(@NonNull FragmentManager fm)
        {
            super(fm);
        }
        @NonNull
        @Override
        public Fragment getItem(int position){
            return fragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return fragmentList.size();
        }
        public void AddFragments(Fragment fragment, String title){
            fragmentList.add(fragment);
            titleList.add(title);
        }
        @NonNull
        @Override
        public CharSequence getPageTitle(int position){
            return titleList.get(position);

        }

    }
}
