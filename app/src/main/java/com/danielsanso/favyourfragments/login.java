package com.danielsanso.favyourfragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class login extends AppCompatActivity {
    Button button=null;
    FloatingActionButton buttonRegis;
    CardView card;
    EditText nombre,pass;
    TextView title,forgotPass;
    RelativeLayout mListLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // getSupportActionBar().hide();//quita el action bar

        mListLayout=(RelativeLayout)findViewById(R.id.listLayout);
        card=(CardView)findViewById(R.id.cardView);
        nombre=(EditText) findViewById(R.id.idLblNombre);
        pass=(EditText) findViewById(R.id.idLblPassword);
        title=(TextView) findViewById(R.id.idEmpresa);
        button=(Button)findViewById(R.id.buttonLogIn);
        buttonRegis=(FloatingActionButton) findViewById(R.id.floatingActionButtonreg);
        forgotPass=(TextView)findViewById(R.id.forgotPass);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Log in
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        buttonRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Boton Registro
                Intent intent= new Intent(getApplicationContext(),registerActivity.class);

                Pair[]pairs = new Pair[6];//Para meter los datos en el act.options, en loa layout tienen q tener mismo nombre
                pairs[0]=new Pair<View,String>(button,"boton");
                pairs[1]=new Pair<View,String>(buttonRegis,"botonRegis");
                pairs[2]=new Pair<View,String>(pass,"pass");
                pairs[3]=new Pair<View,String>(nombre,"nombre");
                pairs[4]=new Pair<View,String>(title,"title");
                pairs[5]=new Pair<View,String>(card,"card");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);

                startActivity(intent,options.toBundle());
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent= new Intent(getApplicationContext(),MainNavig.class);
                startActivity(intent);*/

            }
        });


    }
}