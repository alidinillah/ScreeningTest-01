package com.example.screeningtest01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    Button btnNext;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        etName = findViewById(R.id.etName);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputName(view);
            }
        });
    }

    public void inputName(View view){
        String name = etName.getText().toString();

        Intent i = new Intent(StartActivity.this, MainActivity.class);
        i.putExtra("name", name);
        startActivity(i);
    }
}
