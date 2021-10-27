package com.deltagio.ayakamae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Random;

public class R3a1 extends AppCompatActivity {
    Button btSair, btOk;
    Context context;
    RadioButton rbA,rbB,rbC,rbD;

    //pontuação
    public int rx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r3a1);

        context=this;
        btSair=findViewById(R.id.sair_r3a1);
        btOk=findViewById(R.id.ok_r3a1);
        rbA=findViewById(R.id.a_r3a1);
        rbB=findViewById(R.id.b_r3a1);
        rbC=findViewById(R.id.c_r3a1);
        rbD=findViewById(R.id.d_r3a1);

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msSair();
            }
        });
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbA.isChecked()){
                    //erro
                    msErro();
                } else if(rbB.isChecked()){
                    //erro
                    msErro();
                } else if(rbC.isChecked()){
                    //acerto
                    rx=1; msAcerto(); saveR();
                } else if(rbD.isChecked()) {
                    //erro
                    msErro();
                }
            }
        });
    }

    public void saveR(){
        SharedPreferences key=getSharedPreferences(RFinalActivity.SAVE_REV,0);
        SharedPreferences.Editor editor=key.edit();
        editor.putInt(RFinalActivity.REV_3, rx);
        editor.apply();
    }

    public void msAcerto(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
        dialogo.setTitle("Parabéns!");
        dialogo.setMessage("Você acertou \uD83D\uDE01");
        dialogo.setCancelable(false);

        dialogo.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextScreen();
            }
        });
        dialogo.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    msSair();
                    return true;
                }
                return false;
            }
        });

        dialogo.create().show();
    }
    public void msErro(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
        dialogo.setTitle("Que pena!");
        dialogo.setMessage("Você errou \uD83D\uDE15");
        dialogo.setCancelable(false);

        dialogo.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextScreen();
            }
        });
        dialogo.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    msSair();
                    return true;
                }
                return false;
            }
        });

        dialogo.create().show();
    }

    public void nextScreen(){
        ArrayList<Class> activityList = new ArrayList<>();
        activityList.add(R4a1.class);
        activityList.add(R4a2.class);
        activityList.add(R4a3.class);
        activityList.add(R4a4.class);

        Random generator = new Random();
        int number = generator.nextInt(4) + 1;
        Class revAlea = null;

        switch (number) {
            case 1:
                revAlea = R4a1.class;
                activityList.remove(R4a1.class);
                break;
            case 2:
                revAlea = R4a2.class;
                activityList.remove(R4a2.class);
                break;
            case 3:
                revAlea = R4a3.class;
                activityList.remove(R4a3.class);
                break;
            default:
                revAlea = R4a4.class;
                activityList.remove(R4a4.class);
                break;
        }
        Intent intent = new Intent(getBaseContext(), revAlea);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        msSair();
    }

    public void msSair() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
        dialogo.setTitle("Tem certeza de que quer sair?");
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