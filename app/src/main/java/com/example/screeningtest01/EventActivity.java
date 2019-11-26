package com.example.screeningtest01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class EventActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        setTitle("Event");

        mRecyclerView = findViewById(R.id.listEvent);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event(R.drawable.ic_event, "Magang PKL", "02 Januari 2020"));
        events.add(new Event(R.drawable.ic_event, "Supervisi", "03 Februari 2020"));
        events.add(new Event(R.drawable.ic_event, "Seminar", "16 Maret 2020"));
        events.add(new Event(R.drawable.ic_event, "Sidang", "01 April 2020"));
        events.add(new Event(R.drawable.ic_event, "Yudisium", "15 Mei 2020"));

        mAdapter = new EventAdapter(events, this, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
