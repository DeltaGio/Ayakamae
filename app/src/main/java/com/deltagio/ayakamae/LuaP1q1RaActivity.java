package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LuaP1q1RaActivity extends AppCompatActivity {

    Button lua_btsair_p1q1ra, lua_btok_p1q1ra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luatela_p1q1ra);

        lua_btsair_p1q1ra = (Button) findViewById(R.id.lua_btsair_p1q1ra);
        lua_btok_p1q1ra = (Button) findViewById(R.id.lua_btok_p1q1ra);

        lua_btsair_p1q1ra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(LuaP1q1RaActivity.this);
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

        lua_btok_p1q1ra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LuaP1q2Activity.class);
                startActivity(intent);
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