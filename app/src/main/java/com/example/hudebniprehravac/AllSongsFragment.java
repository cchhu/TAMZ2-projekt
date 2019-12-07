package com.example.hudebniprehravac;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hudebniprehravac.R;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllSongsFragment extends Fragment {
    ListView ListViewSongs;
    ArrayAdapter<String> ArrayAdapterSong;
    String songs[];

    public AllSongsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_songs, container, false);
        ListViewSongs=view.findViewById(R.id.ListViewSongs);
        return view;
    }

    private ArrayList<File> findSongs(File file){

        ArrayList<File> allSongFiles=new ArrayList<>();
        File [] files=file.listFiles();

        for(File f:files)
        {
            if(f.isDirectory() && !f.isHidden()){
                allSongFiles.addAll(findSongs(f));
            }
            else {
                allSongFiles.add(f);
            }
        }
        return allSongFiles;
    }

}
