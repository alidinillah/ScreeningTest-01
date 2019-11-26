package com.example.screeningtest01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{
    private ArrayList<Event> events;
    private Activity activity;
    private Context context;

    public EventAdapter(ArrayList<Event> events, Activity activity, Context context) {
        this.events = events;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_event, parent, false);

        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, final int position) {
        Event event = events.get(position);
        holder.eventName.setText(event.getNama());
        holder.eventDate.setText(event.getTanggal());
        holder.eventImage.setImageResource(event.getImage());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView eventName;
        TextView eventDate;
        ImageView eventImage;
        private int resultCode = 1;

        EventViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            eventName = (TextView) itemView.findViewById(R.id.tvEventName);
            eventDate = (TextView) itemView.findViewById(R.id.tvEventDate);
            eventImage = (ImageView) itemView.findViewById(R.id.ivEvent);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("event", eventName.getText());
                    activity.setResult(resultCode, resultIntent);
                    activity.finish();
                }
            });
        }
    }
}