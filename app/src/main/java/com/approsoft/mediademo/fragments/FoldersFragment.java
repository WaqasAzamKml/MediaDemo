package com.approsoft.mediademo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.approsoft.mediademo.models.Folder;
import com.approsoft.mediademo.adapters.FoldersAdapter;
import com.approsoft.mediademo.R;
import com.approsoft.mediademo.listeners.RecyclerTouchListener;
import com.approsoft.mediademo.utilities.Utils;
import com.approsoft.mediademo.models.Video;

import java.util.ArrayList;
import java.util.List;


public class FoldersFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Video> videos = new ArrayList();
    private List<Folder> folders = new ArrayList<>();
    FoldersAdapter adapter;
    Utils utils;
    FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        utils = new Utils(getActivity().getApplicationContext());
        videos = utils.fetchAllVideos();
        folders = utils.fetchAllFolders();
        adapter = new FoldersAdapter(folders, getActivity().getApplicationContext());
        fragmentManager = getFragmentManager();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_folders, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(0);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(
                getActivity().getApplication(), new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                VideosFragment fragment = new VideosFragment();
                Bundle args = new Bundle();
                args.putString("folderPath", folders.get(i).getPath());
                fragment.setArguments(args);
                fragmentManager.beginTransaction().replace(R.id.frameMain, fragment)
                        .addToBackStack("FoldersFragment").commit();
            }
        }
        ));
        return view;
    }
}
