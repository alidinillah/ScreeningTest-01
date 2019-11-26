package com.example.screeningtest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnEvent, btnGuest;
    TextView tvName;
    private int evCode = 1;
    private int gtCode = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        btnEvent = findViewById(R.id.btnEvent);
        btnGuest = findViewById(R.id.btnGuest);

        Bundle extName = getIntent().getExtras();
        if (extName != null){
            String name = extName.getString("name");
            tvName.setText(name);
        }

        btnEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EventActivity.class);
                startActivityForResult(i, evCode);
            }
        });

        btnGuest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GuestActivity.class);
                startActivityForResult(i, gtCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        if(reqCode == evCode){
            if (data != null){
                String evName = data.getStringExtra("event");
                btnEvent.setText(evName);
            }
        } else if (reqCode == gtCode){
            if (data != null){
                String gtName = data.getStringExtra("guest");
                btnGuest.setText(gtName);
            }
        }
    }
}
