package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class LuaP2q1Activity extends AppCompatActivity {

    Button lua_btsair_p2q1, lua_btduv_p2q1, lua_btok_p2q1;
    RadioButton lua_bta_p2q1, lua_btb_p2q1, lua_btc_p2q1;

    public int n, exer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luatela_p2q1);

        lua_bta_p2q1 = (RadioButton) findViewById(R.id.lua_bta_p2q1);
        lua_btb_p2q1 = (RadioButton) findViewById(R.id.lua_btb_p2q1);
        lua_btc_p2q1 = (RadioButton) findViewById(R.id.lua_btc_p2q1);

        lua_btsair_p2q1 = (Button) findViewById(R.id.lua_btsair_p2q1);
        lua_btok_p2q1 = (Button) findViewById(R.id.lua_btok_p2q1);
        lua_btduv_p2q1 = (Button) findViewById(R.id.lua_btduv_p2q1);

        lua_btok_p2q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (lua_bta_p2q1.isChecked()) {
                    if (exer == 0) {
                        exer=1;
                        SaveScore();
                    } else if(exer==2){
                        exer=1;
                        SaveScore();
                    }
                    n ++;
                    SaveN();
                    Intent intent = new Intent(getApplicationContext(), LuaP2q1RaActivity.class);
                    startActivity(intent);
                } else if (lua_btb_p2q1.isChecked()) {
                    if (exer==1) {
                        exer=2;
                        SaveScore();
                    } else if (exer==2){
                        exer=0;
                        SaveScore();
                    }
                    n ++;
                    SaveN();
                    Intent intent = new Intent(getApplicationContext(), LuaP2q1RbActivity.class);
                    startActivity(intent);
                } else if (lua_btc_p2q1.isChecked()) {
                    if (exer==1) {
                        exer=2;
                        SaveScore();
                    } else if (exer==2){
                        exer=0;
                        SaveScore();
                    }
                    n ++;
                    SaveN();
                    Intent intent = new Intent(getApplicationContext(), LuaP2q1RcActivity.class);
                    startActivity(intent);
                }
            }
        });

        lua_btsair_p2q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(LuaP2q1Activity.this);
                dialogo.setTitle("Tem certeza de que quer sair?");
                dialogo.setMessage("Você terá que recomeçar toda a atividade depois");
                dialogo.setNegativeButton("Não", null);

                dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialogo.show();
            }
        });

        lua_btduv_p2q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LuaP2q1DicaActivity.class);
                startActivity(intent);
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
        editor.putInt(MainActivity.INT_L_P2Q1, exer);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences keyExe=getSharedPreferences(MainActivity.SAVE_INTs,0);
        SharedPreferences keyQTN=getSharedPreferences(MainActivity.SAVE_INTs,0);

        exer=keyExe.getInt(MainActivity.INT_L_P2Q1, 0);
        n=keyQTN.getInt(MainActivity.QTN_N, 0);
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Tem certeza de que quer sair?");
        dialogo.setMessage("Você terá que recomeçar toda a atividade depois");
        dialogo.setNegativeButton("Não", null);

        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        dialogo.show();
    }

}