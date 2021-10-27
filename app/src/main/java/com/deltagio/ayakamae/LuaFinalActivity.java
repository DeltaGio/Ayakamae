package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LuaFinalActivity extends AppCompatActivity {

    Button lua_btok_final;
    public int fim_lua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luatela_final);

        lua_btok_final=findViewById(R.id.lua_btok_final);

        lua_btok_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        Condition();
    }

    public void Condition(){
        if(fim_lua==0){
            fim_lua=1;
            SaveFim();
        }
    }

    public void SaveFim(){
        SharedPreferences keyFim=getSharedPreferences(MainActivity.SAVE_INTs,0);
        SharedPreferences.Editor editor=keyFim.edit();
        editor.putInt(MainActivity.FIM_LUA, fim_lua);
        editor.apply();
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Quer sair?");
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