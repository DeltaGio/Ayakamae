package com.deltagio.ayakamae;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.deltagio.ayakamae.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //botões, progresso, e textos
    ImageButton lua_btprincipal, imgbt_revisao, constel_btprincipal, sol_btprincipal;
    TextView qtn_txt, perc_txt;
    ProgressBar progress_bar;
    Context context;
    ProgressBar prSol,prLua, prConst;


    //Drawer deslizante
    public DrawerLayout drawer;

    //Chave de salvamento de inteiros
    public static final String SAVE_INTs="keys inteiros";


    //Chave de acesso de Int
    //Lua
    public static final String INT_L_P1Q1="lua p1q1", INT_L_P2Q1="lua p2q1",INT_L_P1Q2="lua p1q2",INT_L_P2Q2="lua p2q2",
            INT_L_P2Q3="lua p2q3",INT_L_P3Q1="lua p3q1", INT_L_P4Q1="lua p4q1",INT_L_P4Q2="lua p4q2";
    //Constelação
    public static final String INT_C_P1Q1="Const p1q1",INT_C_P1Q2="Const p1q2",INT_C_P1Q3="Const p1q3",INT_C_P2Q1="Const p2q1",
            INT_C_P2Q2="Const p2q2",INT_C_P3Q1="Const p3q1",INT_C_P3Q2="Const p3q2",INT_C_P3Q3="Const p3q13";
    //Sol
    public static final String INT_S_P1Q1="Sol p1q1",INT_S_P1Q2="Sol p1q2",INT_S_P2Q1="Sol p2q1",INT_S_P2Q2="Sol p2q2";


    //Chave para quantizador de atividades
    public static final String QTN_N="quantizadorAtividades";
    //Chave para outros
    public static final String MS_REV="mostra revisão", MS_INI="mostra mensagem inicio", MS_SURP="mostra 100%",
    FIM_LUA="final da lua",FIM_SOL="final do sol",FIM_CONST="final constelações";


    //progressos
    //Lua
    public int lp1q1_pr,lp1q2_pr,lp2q1_pr,lp2q2_pr,lp2q3_pr,lp3q1_pr,lp4q1_pr,lp4q2_pr;
    //Constelação
    public int cp1q1_pr,cp1q2_pr,cp1q3_pr,cp2q1_pr,cp2q2_pr,cp3q1_pr,cp3q2_pr,cp3q3_pr;
    //Sol
    public int sp1q1_pr,sp1q2_pr,sp2q1_pr,sp2q2_pr;

    //progressos módulos
    //Lua
    public int lpr_p1q1,lpr_p1q2,lpr_p2q1,lpr_p2q2,lpr_p2q3,lpr_p3q1,lpr_p4q1,lpr_p4q2;
    //Constelações
    public int cpr_p1q1,cpr_p1q2,cpr_p1q3,cpr_p2q1,cpr_p2q2,cpr_p3q1,cpr_p3q2,cpr_p3q3;
    //sol
    public int spr_p1q1,spr_p1q2,spr_p2q1,spr_p2q2;


    //inteiros questões
    //Lua
    public int l_p1q1,l_p1q2,l_p2q1,l_p2q2,l_p2q3,l_p3q1,l_p4q1,l_p4q2;
    //Conste
    public int c_p1q1,c_p1q2,c_p1q3,c_p2q1,c_p2q2,c_p3q1,c_p3q2,c_p3q3;
    //Sol
    public int s_p1q1,s_p2q1,s_p2q2,s_p1q2;


    //inteiros quantizador
    public int n;
    //outros
    public int ms_ini, ms_rev, ms_surp, fim_lua,fim_sol,fim_const;
    int r1,r2,r3,r4,r5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); ///para toolbar

        drawer =findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this); //funcionamento do drawer

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState(); //drawer em execução

        //identificação de botões, textos...
        lua_btprincipal = findViewById(R.id.lua_btprincipal);
        progress_bar = findViewById(R.id.progress_bar);
        qtn_txt=findViewById(R.id.qtn_txt);
        perc_txt=findViewById(R.id.perc_txt);
        imgbt_revisao=findViewById(R.id.imgbt_revisao);
        constel_btprincipal=findViewById(R.id.constel_btprincipal);
        sol_btprincipal=findViewById(R.id.sol_btprincipal);
        context=this;

        prSol=findViewById(R.id.progress_sol);
        prLua=findViewById(R.id.progress_lua);
        prConst=findViewById(R.id.progress_const);


        //execuções
        lua_btprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LuaTembeActivity.class);
                startActivity(intent);
            }
        });
        imgbt_revisao.setOnClickListener(new View.OnClickListener() {
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
        constel_btprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConstP1aActivity.class);
                startActivity(intent);
            }
        });
        sol_btprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SolP1aActivity.class);
                startActivity(intent);
            }
        });

        //carregamentos
        LoadData();Check100();CheckRev();CheckIni();
    }
    //Ountras condições
    public void Check100(){
        if(
                //Lua
                l_p1q1==1 && l_p1q2==1 && l_p2q1==1 && l_p2q2==1 && l_p2q3==1 &&
            l_p3q1==1 && l_p4q1==1 && l_p4q2==1 &&
                //constelaçõe
                c_p1q1==1 && c_p1q2==1 && c_p1q3==1 && c_p2q1==1 &&
                        c_p2q2==1 && c_p3q1==1 && c_p3q2==1
                        && c_p3q3==1 &&
                        //Sol
                        s_p1q1==1 && s_p1q2==1 && s_p2q1==1 && s_p2q2==1){
            if(ms_surp==0){
                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle("UAAAU, você acertou tudo!!!");
                dialogo.setMessage("Olhe no menu! Há uma surpresa para você \uD83D\uDE0F");
                dialogo.setNegativeButton("OK", null);

                ms_surp=1;
                SaveSurp();
                dialogo.show();
            }
        }
    }
    public void CheckRev(){
        if(fim_lua==1 && fim_const==1 && fim_sol==1){
            if(ms_rev==0){
                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle("Você já conhece todas atividades que há aqui");
                dialogo.setMessage("Agora você pode optar por revisar as perguntas caso queira! Há um botão na barra superior \uD83D\uDE43");
                dialogo.setNegativeButton("OK", null);

                ms_rev=1;
                SaveMesRev();
                dialogo.show();
            }
        }
    }
    public void CheckIni(){
        if(ms_ini==0){
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("Olá, seja bem-vindo ao Ayakamaé!!!\uD83D\uDE0A");
            dialogo.setMessage("Nesse aplicativo você fará algumas atividades sobre Astronomia indígena brasileira. Mas antes de começar, olhe as instruções no menu!");
            dialogo.setNegativeButton("OK", null);

            ms_ini=1;
            SaveMesIni();
            dialogo.show();
        }
    }

    //Outros Salvamentos
    public void SaveMesRev(){
        SharedPreferences key=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences.Editor editor=key.edit();
        editor.putInt(MS_REV, ms_rev);
        editor.apply();
        VisibilityRev();
    }
    public void SaveSurp(){
        SharedPreferences key=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences.Editor editor=key.edit();
        editor.putInt(MS_SURP, ms_surp);
        editor.apply();
        VisibilitySurp();
    }
    public void SaveMesIni(){
        SharedPreferences key=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences.Editor editor=key.edit();
        editor.putInt(MS_INI, ms_ini);
        editor.apply();
    }

    //Carregador dos inteiros
    public void LoadData(){
        //carregar preferencias
        inteirConst();inteirLua();interSol();

        //preferencias compartilhadas
        SharedPreferences QTN=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences MsSurp=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences MsIni=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences MSRev=getSharedPreferences(SAVE_INTs,0);

        //preferecias resgatadas
        n=QTN.getInt(QTN_N,0);
        ms_surp=MsSurp.getInt(MS_SURP,0);
        ms_ini=MsIni.getInt(MS_INI,0);
        ms_rev=MSRev.getInt(MS_REV, 0);

        //carregamento de status
        QTN_atv(); LoadKeysPercent();loadIdenpendent();

        //Outros
        VisibilitySurp();VisibilityRev();
    }

    public void inteirLua(){
        //preferencias compartilhadas
        SharedPreferences Lp1q1=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Lp1q2=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Lp2q1=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Lp2q2=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Lp2q3=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Lp3q1=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Lp4q1=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Lp4q2=getSharedPreferences(SAVE_INTs,0);

        SharedPreferences keyFim=getSharedPreferences(SAVE_INTs,0);

        //preferecias resgatadas
        l_p1q1=Lp1q1.getInt(INT_L_P1Q1,0);
        l_p1q2=Lp1q2.getInt(INT_L_P1Q2,0);
        l_p2q1=Lp2q1.getInt(INT_L_P2Q1,0);
        l_p2q2=Lp2q2.getInt(INT_L_P2Q2,0);
        l_p2q3=Lp2q3.getInt(INT_L_P2Q3,0);
        l_p3q1=Lp3q1.getInt(INT_L_P3Q1,0);
        l_p4q1=Lp4q1.getInt(INT_L_P4Q1,0);
        l_p4q2=Lp4q2.getInt(INT_L_P4Q2,0);

        fim_lua=keyFim.getInt(FIM_LUA, 0);
    }
    public void inteirConst(){
        //preferencias compartilhadas
        SharedPreferences Cp1q1=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Cp1q2=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Cp1q3=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Cp2q1=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Cp2q2=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Cp3q1=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Cp3q2=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Cp3q3=getSharedPreferences(SAVE_INTs,0);

        SharedPreferences keyFim=getSharedPreferences(SAVE_INTs,0);

        //preferecias resgatadas
        c_p1q1=Cp1q1.getInt(INT_C_P1Q1,0);
        c_p1q2=Cp1q2.getInt(INT_C_P1Q2,0);
        c_p1q3=Cp1q3.getInt(INT_C_P1Q3,0);
        c_p2q1=Cp2q1.getInt(INT_C_P2Q1,0);
        c_p2q2=Cp2q2.getInt(INT_C_P2Q2,0);
        c_p3q1=Cp3q1.getInt(INT_C_P3Q1,0);
        c_p3q2=Cp3q2.getInt(INT_C_P3Q2,0);
        c_p3q3=Cp3q3.getInt(INT_C_P3Q3,0);

        fim_const=keyFim.getInt(FIM_CONST, 0);
    }
    public void interSol(){
        //preferencias compartilhadas
        SharedPreferences Sp1q1=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Sp1q2=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Sp2q1=getSharedPreferences(SAVE_INTs,0);
        SharedPreferences Sp2q2=getSharedPreferences(SAVE_INTs,0);

        SharedPreferences keyFim=getSharedPreferences(SAVE_INTs,0);

        //preferecias resgatadas
        s_p1q1=Sp1q1.getInt(INT_S_P1Q1,0);
        s_p1q2=Sp1q2.getInt(INT_S_P1Q2,0);
        s_p2q1=Sp2q1.getInt(INT_S_P2Q1,0);
        s_p2q2=Sp2q2.getInt(INT_S_P2Q2,0);

        fim_sol=keyFim.getInt(FIM_SOL, 0);
    }

    //percentual Geral das perguntas
    public void LoadKeysPercent(){
        //Lua
        PerLp1q1();PerLp1q2();PerLp2q2();PerLp2q3();
        PerLp2q1();PerLp3q1();PerLp4q1();PerLp4q2();

        //Constelações
        PerCp1q1();PerCp1q2();PerCp1q3();PerCp2q1();
        PerCp2q2();PerCp3q1();PerCp3q2();PerCp3q3();
        //Sol
        PerSp1q1();PerSp1q2();PerSp2q1();PerSp2q2();
    }
    //Lua//-------------xxx---------------------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public void PerLp1q1(){
        if(l_p1q1==1){
            lp1q1_pr +=5;
        } else if(l_p1q1==2){
            if(lp1q1_pr >=1){
                lp1q1_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerLp1q2(){
        if(l_p1q2==1){
            lp1q2_pr +=5;
        } else if(l_p1q2==2){
            if(lp1q2_pr >=1){
                lp1q2_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerLp2q1(){
        if(l_p2q1==1){
            lp2q1_pr +=5;
        } else if(l_p2q1==2){
            if(lp2q1_pr >=1){
                lp2q1_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerLp2q2(){
        if(l_p2q2==1){
            lp2q2_pr +=5;
        } else if(l_p2q2==2){
            if(lp2q2_pr >=1){
                lp2q2_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerLp2q3(){
        if(l_p2q3==1){
            lp2q3_pr +=5;
        } else if(l_p2q3==2){
            if(lp2q3_pr >=1){
                lp2q3_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerLp3q1(){
        if(l_p3q1==1){
            lp3q1_pr +=5;
        } else if(l_p3q1==2){
            if(lp3q1_pr >=1){
                lp3q1_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerLp4q1(){
        if(l_p4q1==1){
            lp4q1_pr +=5;
        } else if(l_p4q1==2){
            if(lp4q1_pr >=1){
                lp4q1_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerLp4q2(){
        if(l_p4q2==1){
            lp4q2_pr +=5;
        } else if(l_p4q2==2){
            if(lp4q2_pr >=1){
                lp4q2_pr -=5;
            }
        }
        UpdatePercent();
    }
    //----------------xxxxx--------------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    //Constelações
    public void PerCp1q1(){
        if(c_p1q1==1){
            cp1q1_pr +=5;
        } else if(c_p1q1==2){
            if(cp1q1_pr >=1){
                cp1q1_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerCp1q2(){
        if(c_p1q2==1){
            cp1q2_pr +=5;
        } else if(c_p1q2==2){
            if(cp1q2_pr >=1){
                cp1q2_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerCp1q3(){
        if(c_p1q3==1){
            cp1q3_pr +=5;
        } else if(c_p1q3==2){
            if(cp1q3_pr >=1){
                cp1q3_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerCp2q1(){
        if(c_p2q1==1){
            cp2q1_pr +=5;
        } else if(c_p2q1==2){
            if(cp2q1_pr >=1){
                cp2q1_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerCp2q2(){
        if(c_p2q2==1){
            cp2q2_pr +=5;
        } else if(c_p2q2==2){
            if(cp2q2_pr >=1){
                cp2q2_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerCp3q1(){
        if(c_p3q1==1){
            cp3q1_pr +=5;
        } else if(c_p3q1==2){
            if(cp3q1_pr >=1){
                cp3q1_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerCp3q2(){
        if(c_p3q2==1){
            cp3q2_pr +=5;
        } else if(c_p3q2==2){
            if(cp3q2_pr >=1){
                cp3q2_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerCp3q3(){
        if(c_p3q3==1){
            cp3q3_pr +=5;
        } else if(c_p3q3==2){
            if(cp3q3_pr >=1){
                cp3q3_pr -=5;
            }
        }
        UpdatePercent();
    }
    //Sol//-------------xxx---------------------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    public void PerSp1q1(){
        if(s_p1q1==1){
            sp1q1_pr +=5;
        } else if(s_p1q1==2){
            if(sp1q1_pr >=1){
                sp1q1_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerSp1q2(){
        if(s_p1q2==1){
            sp1q2_pr +=5;
        } else if(s_p1q2==2){
            if(sp1q2_pr >=1){
                sp1q2_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerSp2q1(){
        if(s_p2q1==1){
            sp2q1_pr +=5;
        } else if(s_p2q1==2){
            if(sp2q1_pr >=1){
                sp2q1_pr -=5;
            }
        }
        UpdatePercent();
    }
    public void PerSp2q2(){
        if(s_p2q2==1){
            sp2q2_pr +=5;
        } else if(s_p2q2==2){
            if(sp2q2_pr >=1){
                sp2q2_pr -=5;
            }
        }
        UpdatePercent();
    }
    //progressBar Geral
    public void UpdatePercent(){
        progress_bar.setProgress(
                //lua
                lp1q1_pr+lp1q2_pr+lp2q1_pr+lp2q2_pr+lp2q3_pr+lp3q1_pr+lp4q1_pr+lp4q2_pr+
                //constelações
                cp1q1_pr+cp1q2_pr+cp1q3_pr+cp2q1_pr+cp2q2_pr+cp3q1_pr+cp3q2_pr+cp3q3_pr+
                        //sol
                        sp1q1_pr+sp1q2_pr+sp2q1_pr+sp2q2_pr);
        perc_txt.setText(
                //lua
                lp1q1_pr+lp1q2_pr+lp2q1_pr+lp2q2_pr+lp2q3_pr+lp3q1_pr+lp4q1_pr+lp4q2_pr+
                        //constelações
                        cp1q1_pr+cp1q2_pr+cp1q3_pr+cp2q1_pr+cp2q2_pr+cp3q1_pr+cp3q2_pr+cp3q3_pr+
                        //sol
                        sp1q1_pr+sp1q2_pr+sp2q1_pr+sp2q2_pr+
                        "%");
    }

    //Quantizador
    public void QTN_atv(){
        if(n==1){
            qtn_txt.setText(n+ " tentativa de 20 questões");
        } else if(n>=2){
            qtn_txt.setText(n+ " tentativas de 20 questões");
        }
    }

    //outros retornos
    public void VisibilitySurp(){
        if(ms_surp==1){
            NavigationView navigationView = findViewById(R.id.nav_view);
            Menu nav_menu2 = navigationView.getMenu();
            nav_menu2.findItem(R.id.nav_surp).setVisible(true);
        }
    }
    public void VisibilityRev(){ if(ms_rev==1){imgbt_revisao.setVisibility(VISIBLE);} }

    //progressos independentes
    public void loadIdenpendent(){
        //Lua
        Luap1q1();Luap2q1();Luap1q2();Luap2q2();
        Luap2q3();Luap3q1();Luap4q1();Luap4q2();
        //Constelações
        Constp1q1();Constp1q2();Constp1q3();Constp2q1();
        Constp2q2();Constp3q1();Constp3q2();Constp3q3();
        //Sol
        Solp1q1();Solp1q2();Solp2q1();Solp2q2();
    }

    //-------xxxxxxxxxxxxxxxxxxxxxxxxxxxx-------------------------------------------------
    //Lua
    public void Luap1q1(){
        if(l_p1q1==1){
            lpr_p1q1 +=12;
        } else if(l_p1q1==2){
            if(lpr_p1q1 >=1){
                lpr_p1q1 -=12;
            }
        }
        percentLua();
    }
    public void Luap1q2(){
        if(l_p1q2==1){
            lpr_p1q2 +=12;
        } else if(l_p1q2==2){
            if(lpr_p1q2 >=1){
                lpr_p1q2 -=12;
            }
        }
        percentLua();
    }
    public void Luap2q1(){
        if(l_p2q1==1){
            lpr_p2q1 +=13;
        } else if(l_p2q1==2){
            if(lpr_p2q1 >=1){
                lpr_p2q1 -=13;
            }
        }
        percentLua();
    }
    public void Luap2q2(){
        if(l_p2q2==1){
            lpr_p2q2 +=13;
        } else if(l_p2q2==2){
            if(lpr_p2q2 >=1){
                lpr_p2q2 -=13;
            }
        }
        percentLua();
    }
    public void Luap2q3(){
        if(l_p2q3==1){
            lpr_p2q3 +=13;
        } else if(l_p2q3==2){
            if(lpr_p2q3 >=1){
                lpr_p2q3 -=13;
            }
        }
        percentLua();
    }
    public void Luap3q1(){
        if(l_p3q1==1){
            lpr_p3q1 +=13;
        } else if(l_p3q1==2){
            if(lpr_p3q1 >=1){
                lpr_p3q1 -=13;
            }
        }
        percentLua();
    }
    public void Luap4q1(){
        if(l_p4q1==1){
            lpr_p4q1 +=12;
        } else if(l_p4q1==2){
            if(lpr_p4q1 >=1){
                lpr_p4q1 -=12;
            }
        }
        percentLua();
    }
    public void Luap4q2(){
        if(l_p4q2==1){
            lpr_p4q2 +=12;
        } else if(l_p4q2==2){
            if(lpr_p4q2 >=1){
                lpr_p4q2 -=12;
            }
        }
        percentLua();
    }
    //Percent Lua --------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx-------------------------------
    public void percentLua(){
        prLua.setProgress(lpr_p1q1+lpr_p2q1+lpr_p1q2+lpr_p2q2+lpr_p2q3+lpr_p3q1+lpr_p4q1+lpr_p4q2);
    }

    //-------xxxxxxxxxxxxxxxxxxxxxxxxxxxx-------------------------------------------------
    //Constelações
    public void Constp1q1(){
        if(c_p1q1==1){
            cpr_p1q1 +=12;
        } else if(c_p1q1==2){
            if(cpr_p1q1 >=1){
                cpr_p1q1 -=12;
            }
        }
        percentConst();
    }
    public void Constp1q2(){
        if(c_p1q2==1){
            cpr_p1q2 +=13;
        } else if(c_p1q2==2){
            if(cpr_p1q2 >=1){
                cpr_p1q2 -=13;
            }
        }
        percentConst();
    }
    public void Constp1q3(){
        if(c_p1q3==1){
            cpr_p1q3 +=12;
        } else if(c_p1q3==2){
            if(cpr_p1q3 >=1){
                cpr_p1q3 -=12;
            }
        }
        percentConst();
    }
    public void Constp2q1(){
        if(c_p2q1==1){
            cpr_p2q1 +=13;
        } else if(c_p2q1==2){
            if(cpr_p2q1 >=1){
                cpr_p2q1 -=13;
            }
        }
        percentConst();
    }
    public void Constp2q2(){
        if(c_p2q2==1){
            cpr_p2q2 +=12;
        } else if(c_p2q2==2){
            if(cpr_p2q2 >=1){
                cpr_p2q2 -=12;
            }
        }
        percentConst();
    }
    public void Constp3q1(){
        if(c_p3q1==1){
            cpr_p3q1 +=13;
        } else if(c_p3q1==2){
            if(cpr_p3q1 >=1){
                cpr_p3q1 -=13;
            }
        }
        percentConst();
    }
    public void Constp3q2(){
        if(c_p3q2==1){
            cpr_p3q2 +=12;
        } else if(c_p3q2==2){
            if(cpr_p3q2 >=1){
                cpr_p3q2 -=12;
            }
        }
        percentConst();
    }
    public void Constp3q3(){
        if(c_p3q3==1){
            cpr_p3q3 +=13;
        } else if(c_p3q3==2){
            if(cpr_p3q3 >=1){
                cpr_p3q3 -=13;
            }
        }
        percentConst();
    }
    //Percent Constelações--------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx-------------------------------
    public void percentConst(){
        prConst.setProgress(cpr_p1q1+cpr_p1q2+cpr_p1q3+cpr_p2q1+cpr_p2q2+cpr_p3q1+
                cpr_p3q2+cpr_p3q3);
    }

    //-------xxxxxxxxxxxxxxxxxxxxxxxxxxxx-------------------------------------------------
    //Sol
    public void Solp1q1(){
        if(s_p1q1==1){
            spr_p1q1 +=25;
        } else if(s_p1q1==2){
            if(spr_p1q1 >=1){
                spr_p1q1 -=25;
            }
        }
        percentSol();
    }
    public void Solp1q2(){
        if(s_p1q2==1){
            spr_p1q2 +=25;
        } else if(s_p1q2==2){
            if(spr_p1q2 >=1){
                spr_p1q2 -=25;
            }
        }
        percentSol();
    }
    public void Solp2q1(){
        if(s_p2q1==1){
            spr_p2q1 +=25;
        } else if(s_p2q1==2){
            if(spr_p2q1 >=1){
                spr_p2q1 -=25;
            }
        }
        percentSol();
    }
    public void Solp2q2(){
        if(s_p2q2==1){
            spr_p2q2 +=25;
        } else if(s_p2q2==2){
            if(spr_p2q2 >=1){
                spr_p2q2 -=25;
            }
        }
        percentSol();
    }
    //Percent Sol--------xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx-------------------------------
    public void percentSol(){
        prSol.setProgress(spr_p1q1+spr_p1q2+
                spr_p2q1+spr_p2q2);
    }

    //---------------------xxxxxxxxxxxxxx-----------------------revisão
    public void zerarRs(){
        r1=0; r2=0; r3=0; r4=0; r5=0;
        saveRev();
    }
    public void saveRev(){
        SharedPreferences key=getSharedPreferences(RFinalActivity.SAVE_REV,0);
        SharedPreferences.Editor editor=key.edit();
        editor.putInt(RFinalActivity.REV_1, r1);
        editor.putInt(RFinalActivity.REV_2, r2);
        editor.putInt(RFinalActivity.REV_3, r3);
        editor.putInt(RFinalActivity.REV_4, r4);
        editor.putInt(RFinalActivity.REV_5, r5);
        editor.apply();
    }


    //Drawer e funções
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_instr:
                Intent intent = new Intent(context, Instru1.class);
                startActivity(intent);
                break;
        }
        switch (item.getItemId()) {
            case R.id.nav_sobre:
                Intent intent = new Intent(context, Sobre.class);
                startActivity(intent);
                break;

        } switch (item.getItemId()) {
            case R.id.nav_surp:
                Intent intent = new Intent(context, Surpresa.class);
                startActivity(intent);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finishAffinity();
        }
    }
}