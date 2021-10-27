package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Instru3 extends AppCompatActivity {
    Button instOk, instSair, btBack;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instru3);

        context=this;
        instOk=findViewById(R.id.ok_instr3);
        instSair=findViewById(R.id.sair_instr3);
        btBack=findViewById(R.id.back_instr3);

        instSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        instOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Instru4.class);
                startActivity(intent);
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Instru2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, Instru2.class);
        startActivity(intent);
    }
}