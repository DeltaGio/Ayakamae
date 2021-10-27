package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Instru1 extends AppCompatActivity {
    Button instOk, instSair;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instru1);

        context=this;
        instOk=findViewById(R.id.ok_instr1);
        instSair=findViewById(R.id.sair_instr1);

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
                Intent intent = new Intent(context, Instru2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }
}