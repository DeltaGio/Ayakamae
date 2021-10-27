package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ConstP3aActivity extends AppCompatActivity {
    Context context;
    RadioButton rbSim, rbNao;
    Button btOk, btSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.const_p3a);

        context = this;
        rbNao=findViewById(R.id.c_nao_ap3);
        rbSim=findViewById(R.id.c_sim_ap3);
        btOk=findViewById(R.id.c_ok_ap3);
        btSair=findViewById(R.id.c_sair_ap3);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbSim.isChecked()) {
                    Intent intent = new Intent(context, ConstP3q1Activity.class);
                    startActivity(intent);
                } else if (rbNao.isChecked()) {
                    Intent intent = new Intent(context, ConstP3eActivity.class);
                    startActivity(intent);
                }
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
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