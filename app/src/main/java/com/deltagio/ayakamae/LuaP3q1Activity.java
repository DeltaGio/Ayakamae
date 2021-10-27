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
import android.widget.RadioButton;

public class LuaP3q1Activity extends AppCompatActivity {
    Context context;
    Button bSair, bOk,bDica;
    RadioButton rA,rB,rC,rD;

    public int exer;
    public int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lua_p3q1);

        context=this;
        bDica=findViewById(R.id.l_dica_p3q1);
        bOk=findViewById(R.id.l_ok_p3q1);
        bSair=findViewById(R.id.l_sair_p3q1);
        rA=findViewById(R.id.l_a_p3q1);
        rB=findViewById(R.id.l_b_p3q1);
        rC=findViewById(R.id.l_c_p3q1);
        rD=findViewById(R.id.l_d_p3q1);

        bDica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LuaP3q1Dica.class);
                startActivity(intent);
            }
        });
        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (rA.isChecked()) {
                    //errado
                    if (exer==1) {
                        exer=2;
                        SaveScore();
                    } else if (exer==2){
                        exer=0;
                        SaveScore();
                    }
                    n ++;
                    SaveN();
                    Intent intent = new Intent(context, LuaP3q1A.class);
                    startActivity(intent);
                }else if (rB.isChecked()) {
                    //errado
                    if (exer==1) {
                        exer=2;
                        SaveScore();
                    } else if (exer==2){
                        exer=0;
                        SaveScore();
                    }
                    n ++;
                    SaveN();
                    Intent intent = new Intent(context, LuaP3q1B.class);
                    startActivity(intent);
                }else if (rC.isChecked()) {
                    //errado
                    if (exer==1) {
                        exer=2;
                        SaveScore();
                    } else if (exer==2){
                        exer=0;
                        SaveScore();
                    }
                    n ++;
                    SaveN();
                    Intent intent = new Intent(context, LuaP3q1C.class);
                    startActivity(intent);
                }else if (rD.isChecked()) {
                    //correto
                    if (exer == 0) {
                        exer=1;
                        SaveScore();
                    } else if(exer==2){
                        exer=1;
                        SaveScore();
                    }
                    n ++;
                    SaveN();
                    Intent intent = new Intent(context, LuaP3q1D.class);
                    startActivity(intent);
                }

            }
        });
        bSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
                dialogo.setTitle("Tem certeza de que quer sair?");
                dialogo.setMessage("Você terá que recomeçar toda a atividade depois");
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
        });
        loadData();
    }

    public void SaveN(){
        SharedPreferences keyQTN=getSharedPreferences(MainActivity.SAVE_INTs,0);
        SharedPreferences.Editor editor=keyQTN.edit();
        editor.putInt(MainActivity.QTN_N, n);
        editor.apply();
    }

    public void SaveScore(){
        SharedPreferences keyExe=getSharedPreferences(MainActivity.SAVE_INTs,0);
        SharedPreferences.Editor editor=keyExe.edit();
        editor.putInt(MainActivity.INT_L_P3Q1, exer);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences keyExe=getSharedPreferences(MainActivity.SAVE_INTs,0);
        SharedPreferences keyQTN=getSharedPreferences(MainActivity.SAVE_INTs,0);

        exer=keyExe.getInt(MainActivity.INT_L_P3Q1, 0);
        n=keyQTN.getInt(MainActivity.QTN_N, 0);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
        dialogo.setTitle("Tem certeza de que quer sair?");
        dialogo.setMessage("Você terá que recomeçar toda a atividade depois");
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