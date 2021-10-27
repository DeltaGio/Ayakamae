package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class LuaP2aActivity extends AppCompatActivity {

    RadioButton lua_btsimp2i, lua_btnaop2i;
    Button lua_btok_p2i, lua_btsair_ap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luatela_p2a);

        lua_btnaop2i = (RadioButton) findViewById(R.id.lua_btnaop2i);
        lua_btsimp2i = (RadioButton) findViewById(R.id.lua_btsimp2i);

        lua_btok_p2i = (Button) findViewById(R.id.lua_btok_p2i);
        lua_btsair_ap2 = (Button) findViewById(R.id.lua_btsair_ap2);

        lua_btsair_ap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(LuaP2aActivity.this);
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

        lua_btok_p2i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lua_btsimp2i.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), LuaP2q1Activity.class);
                    startActivity(intent);
                } else if (lua_btnaop2i.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), LuaP2eActivity.class);
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