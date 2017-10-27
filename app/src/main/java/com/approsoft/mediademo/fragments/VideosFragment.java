package com.approsoft.mediademo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.approsoft.mediademo.models.Folder;
import com.approsoft.mediademo.R;
import com.approsoft.mediademo.listeners.RecyclerTouchListener;
import com.approsoft.mediademo.utilities.Utils;
import com.approsoft.mediademo.models.Video;
import com.approsoft.mediademo.activities.VideoPlayerActivity;
import com.approsoft.mediademo.adapters.VideosAdapter;

import java.util.ArrayList;
import java.util.List;


public class VideosFragment extends Fragment {

    RecyclerView recyclerView;
    private List<Video> videos = new ArrayList();
    private List<Video> folderVideos = new ArrayList();
    private List<Folder> folders = new ArrayList<>();
    VideosAdapter adapter;
    Utils utils;
    String folderPath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try{
            folderPath = getArguments().getString("folderPath");
        } catch (Exception e){

        }

        utils = new Utils(getActivity().getApplicationContext());
        if(folderPath!=null){
            videos = utils.fetchVideosByFolder(folderPath);
        }else {
            videos = utils.fetchAllVideos();
        }
//        folders = utils.fetchAllFolders();
//        folderPath = folders.get(3).getPath();
//        folderVideos = utils.fetchVideosByFolder(folderPath);
        adapter = new VideosAdapter(videos, getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(0);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplication(),
                new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), VideoPlayerActivity.class);
                        intent.putExtra("uriString", videos.get(i).getData());
                        intent.putExtra("videoTitle", videos.get(i).getTitle());
                        startActivity(intent);
                    }
                }));
        return view;
    }

}
