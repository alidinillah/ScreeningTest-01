package com.example.screeningtest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuestActivity extends AppCompatActivity {

    private int resultCode = 2;
    private GuestAdapter adapter;
    private GridView gridView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        setTitle("Guest");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<List<Guest>> call = service.getGuests();

        call.enqueue(new Callback<List<Guest>>() {
            @Override
            public void onResponse(Call<List<Guest>> call, Response<List<Guest>> response) {
                try {
                    final List<Guest> results = response.body();
                    for (int i = 0; i < results.size(); i++) {
                        adapter.add(new Guest(results.get(i).getId(), results.get(i).getNama(), results.get(i).getBirthDate()));

                        final int finalI = i;
                        realm.executeTransaction(new Realm.Transaction() {

                            @Override
                            public void execute(Realm realm) {
                                Guest guest = realm.createObject(Guest.class, results.get(finalI).getId());
                                guest.setNama(results.get(finalI).getNama());
                                guest.setBirthDate(results.get(finalI).getBirthDate());
                            }
                        });
                    }


                } catch (Exception e) {
                    Log.d("onResponse", "Check up your internet connection");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Guest>> call, Throwable t) {
                Log.d("onFailure", t.toString());
                RealmResults<Guest> results = realm.where(Guest.class).findAll();
                for (int i = 0; i < results.size(); i++) {
                    adapter.add(new Guest(results.get(i).getId(), results.get(i).getNama(), results.get(i).getBirthDate()));
                }
            }
        });

        final ArrayList<Guest> guests = new ArrayList<>();
        adapter = new GuestAdapter(this, guests);

        gridView = (GridView) findViewById(R.id.gridGuest);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Guest guest = (Guest) gridView.getItemAtPosition(position);
                String guestName = guest.getNama();

                String guestBirthDate = guest.getBirthDate();
                String[] date = guestBirthDate.split("-");

                int birthDate = Integer.parseInt(date[2]);

                if ((birthDate % 2 ==0) && (birthDate % 3 == 0)) {
                    Toast.makeText(GuestActivity.this, "iOS", Toast.LENGTH_LONG).show();
                } else if (birthDate % 2 == 0) {
                    Toast.makeText(GuestActivity.this, "Blackberry", Toast.LENGTH_LONG).show();
                } else if (birthDate % 3 == 0) {
                    Toast.makeText(GuestActivity.this, "Android", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(GuestActivity.this, "Feature Phone", Toast.LENGTH_LONG).show();
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("guest", guestName);

                setResult(resultCode, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
