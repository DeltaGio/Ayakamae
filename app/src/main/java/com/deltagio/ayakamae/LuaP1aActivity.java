package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class LuaP1aActivity extends AppCompatActivity {

    RadioButton lua_btsimp1i, lua_btnaop1i;
    Button lua_btok_p1i, lua_btsair_ap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luatela_p1a);

        lua_btnaop1i = (RadioButton) findViewById(R.id.lua_btnaop1i);
        lua_btsimp1i = (RadioButton) findViewById(R.id.lua_btsimp1i);

        lua_btok_p1i = (Button) findViewById(R.id.lua_btok_p1i);
        lua_btsair_ap1 = (Button) findViewById(R.id.lua_btsair_ap1);

        lua_btsair_ap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(LuaP1aActivity.this);
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

        lua_btok_p1i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lua_btsimp1i.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), LuaP1q1Activity.class);
                    startActivity(intent);
                } else if (lua_btnaop1i.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), LuaP1eActivity.class);
                    startActivity(intent);
                }
            }
        });
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