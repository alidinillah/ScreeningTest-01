package com.example.screeningtest01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GuestAdapter extends ArrayAdapter<Guest> {
    public GuestAdapter(Context context, ArrayList<Guest> guests) {
        super(context, 0, guests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Guest guest = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_guest, parent, false);
        }

        TextView guestName = (TextView) convertView.findViewById(R.id.tvGuest);

        guestName.setText(guest.getNama());
        guestName.setBackgroundResource(R.drawable.ic_guest);

        return convertView;
    }
}
