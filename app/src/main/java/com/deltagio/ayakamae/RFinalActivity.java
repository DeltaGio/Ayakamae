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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class RFinalActivity extends AppCompatActivity {
    Button btRev, btSair;
    TextView tvResult;
    Context context;

    public static final String SAVE_REV="save inteiros de revisão";

    public static final String REV_1="inteiro rev1", REV_2="inteiro rev2", REV_3="inteiro rev3",
            REV_4="inteiro rev4",REV_5="inteiro rev5";

    public int r1,r2,r3,r4,r5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_final);

        context=this;
        btRev=findViewById(R.id.r_rev_final);
        btSair=findViewById(R.id.r_sair_final);
        tvResult=findViewById(R.id.tv_result);

        btRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zerarRs();

                ArrayList<Class> activityList = new ArrayList<>();
                activityList.add(R1a1.class);
                activityList.add(R1a2.class);
                activityList.add(R1a3.class);
                activityList.add(R1a4.class);

                Random generator = new Random();
                int number = generator.nextInt(4) + 1;
                Class revAlea = null;

                switch (number) {
                    case 1:
                        revAlea = R1a1.class;
                        activityList.remove(R1a1.class);
                        break;
                    case 2:
                        revAlea = R1a2.class;
                        activityList.remove(R1a2.class);
                        break;
                    case 3:
                        revAlea = R1a3.class;
                        activityList.remove(R1a3.class);
                        break;
                    default:
                        revAlea = R1a4.class;
                        activityList.remove(R1a4.class);
                        break;
                }
                Intent intent = new Intent(context, revAlea);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zerarRs();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        loadkeys();
    }

    public void loadkeys(){
        //preferencias compartilhadas
        SharedPreferences R1=getSharedPreferences(SAVE_REV,0);
        SharedPreferences R2=getSharedPreferences(SAVE_REV,0);
        SharedPreferences R3=getSharedPreferences(SAVE_REV,0);
        SharedPreferences R4=getSharedPreferences(SAVE_REV,0);
        SharedPreferences R5=getSharedPreferences(SAVE_REV,0);

        //preferecias resgatadas
        r1=R1.getInt(REV_1,0);
        r2=R2.getInt(REV_2,0);
        r3=R3.getInt(REV_3,0);
        r4=R4.getInt(REV_4,0);
        r5=R5.getInt(REV_5,0);

        //outros
        updateText();
    }

    public void updateText(){
        if (r1+r2+r3+r4+r5==1){
            tvResult.setText(r1+r2+r3+r4+r5+" acerto de 5 questões");
        } else if (r1+r2+r3+r4+r5>=2 && r1+r2+r3+r4+r5<=4){
            tvResult.setText(r1+r2+r3+r4+r5+" acertos de 5 questões");
        } else if (r1+r2+r3+r4+r5==5){
            tvResult.setText("Você acertou todas as 5 questões! \uD83E\uDD73");
        }
    }

    public void zerarRs(){
        r1=0; r2=0; r3=0; r4=0; r5=0;
        saveRev();
    }
    public void saveRev(){
        SharedPreferences key=getSharedPreferences(SAVE_REV,0);
        SharedPreferences.Editor editor=key.edit();
        editor.putInt(REV_1, r1);
        editor.putInt(REV_2, r2);
        editor.putInt(REV_3, r3);
        editor.putInt(REV_4, r4);
        editor.putInt(REV_5, r5);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);
        dialogo.setTitle("Quer sair?");
        dialogo.setNegativeButton("Não", null);

        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                zerarRs();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
        dialogo.show();
    }
}