package com.example.hudebniprehravac;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hudebniprehravac.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllSongsFragment extends Fragment {
    ListView ListViewSongs;
    ArrayAdapter<String> ArrayAdapterSong;
    String songs[];
    ArrayList<File> FileSongs;

    public AllSongsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_songs, container, false);
        ListViewSongs=view.findViewById(R.id.ListViewSongs);


        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {

            //noinspection deprecation
            FileSongs=findSongs(Environment.getExternalStorageDirectory());
            songs=new String[FileSongs.size()];

            for(int i=0;i<FileSongs.size();i++){
                songs[i]=FileSongs.get(i).getName();
            }

            ArrayAdapterSong=new ArrayAdapter<>(getActivity(),R.layout.row,songs);
            ListViewSongs.setAdapter(ArrayAdapterSong);

                ListViewSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent player=new Intent(getActivity(),Player.class);

                        player.putExtra("songFileList",songs);
                        player.putExtra("position",position);
                        startActivity(player);

                    }
                });

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
            token.continuePermissionRequest();
            }
        }).check();

        return view;
    }

    private ArrayList<File> findSongs(File file){
        ArrayList<File> allSongFiles=new ArrayList<>();
        File [] files=file.listFiles();
        if (files != null) {
            for(File f:files)
            {
                if(f.isDirectory() && !f.isHidden()){
                    allSongFiles.addAll(findSongs(f));
                }
                else {
                    if(f.getName().endsWith(".mp3")||f.getName().endsWith(".mp4a")||f.getName().endsWith(".wav")||f.getName().endsWith(".mp4p")||f.getName().endsWith(".wma"))
                    allSongFiles.add(f);
                }
            }
        }
        return allSongFiles;
    }

}
