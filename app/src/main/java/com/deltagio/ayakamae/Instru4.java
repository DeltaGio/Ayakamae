package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Instru4 extends AppCompatActivity {
    Button btOk, btSair, btBack;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instru4);

        context=this;
        btOk=findViewById(R.id.ok_instr4);
        btSair=findViewById(R.id.sair_instr4);
        btBack=findViewById(R.id.back_instr4);

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Instru5.class);
                startActivity(intent);
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Instru3.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, Instru3.class);
        startActivity(intent);
    }
}