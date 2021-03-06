package com.momobites.prash.varnamaala.Category04Four;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momobites.prash.varnamaala.Data.Data04Relation;
import com.momobites.prash.varnamaala.Data.Data07Others;
import com.momobites.prash.varnamaala.ModelAdapters.WordAdapter;
import com.momobites.prash.varnamaala.R;

/**
 * Created by prash on 7/1/2017.
 */

public class Extended extends Fragment {

    public Extended(){
        // Required Empty public Constructor
    }


    RecyclerView recyclerView;
    Context context;
    WordAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate layout for this Fragment
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        // Fix the RecycleView to the inflated Fragment
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView);
        // Setting Up Data - fill data
        adapter = new WordAdapter(getContext(), Data04Relation.getExtended());
        // Set Adapter
        recyclerView.setAdapter(adapter);
        // Set Layout Manager

        // Vertical Orientation By Default
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Return
        return rootView;

    }

    @Override
    public void onStop() {
        super.onStop();
    }

}

