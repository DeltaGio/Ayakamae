package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SolFinalActivity extends AppCompatActivity {
    Button btFinal;
    Context context;

    public int fim_sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sol_final);

        context=this;
        btFinal=findViewById(R.id.s_ok_final);

        btFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
        Condition();
    }
    public void Condition(){
        if(fim_sol==0){
            fim_sol=1;
            SaveFim();
        }
    }

    public void SaveFim(){
        SharedPreferences keyFim=getSharedPreferences(MainActivity.SAVE_INTs,0);
        SharedPreferences.Editor editor=keyFim.edit();
        editor.putInt(MainActivity.FIM_SOL, fim_sol);
        editor.apply();
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
        dialogo.setTitle("Quer sair?");
        dialogo.setNegativeButton("Não", null);

        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
        dialogo.show();
    }
}