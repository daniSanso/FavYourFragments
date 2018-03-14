package com.danielsanso.favyourfragments;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerActivity extends AppCompatActivity {
    Button button;
    FloatingActionButton buttonCancel;
    CardView card;
    EditText nombre,pass,email,bloque,piso;
    TextView title;
    RelativeLayout mListLayout;
    FirebaseAuth mAuth;
    ProgressDialog mProgress;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //getSupportActionBar().hide();//quita el action bar
        button=(Button)findViewById(R.id.buttonLogIn);
        buttonCancel=(FloatingActionButton) findViewById(R.id.floatingActionButtonCancel);
        mListLayout=(RelativeLayout)findViewById(R.id.listLayout);
        card=(CardView)findViewById(R.id.cardView);
        nombre=(EditText) findViewById(R.id.idLblNombre);
        email=(EditText) findViewById(R.id.idlblapellido);
        pass=(EditText) findViewById(R.id.idLblPassword);
        bloque=(EditText) findViewById(R.id.idlbloque);
        piso=(EditText) findViewById(R.id.idpiso);
        title=(TextView) findViewById(R.id.idEmpresa);
        mAuth=FirebaseAuth.getInstance();

        mProgress=new ProgressDialog(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
                /*
                Intent intent= new Intent(getApplicationContext(),login.class);
                startActivity(intent);*/

            }
        });
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Intent intent= new Intent(getApplicationContext(),login.class);
                    startActivity(intent);
                }
            }
        };

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),login.class);

                Pair[]pairs = new Pair[6];//Para meter los datos en el act.options, en loa layout tienen q tener mismo nombre
                pairs[0]=new Pair<View,String>(button,"boton");
                pairs[1]=new Pair<View,String>(buttonCancel,"botonRegis");
                pairs[2]=new Pair<View,String>(pass,"pass");
                pairs[3]=new Pair<View,String>(nombre,"nombre");
                pairs[4]=new Pair<View,String>(title,"title");
                pairs[5]=new Pair<View,String>(card,"card");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(registerActivity.this,pairs);

                startActivity(intent,options.toBundle());

            }
        });

    }

    private void startRegister() {
        final String name=nombre.getText().toString().trim();
        final String mail=email.getText().toString().trim();
        final String password=pass.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(password)&&!TextUtils.isEmpty(mail)){
            mProgress.setMessage("Registrando, espera...");
            mProgress.show();

            mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    mProgress.dismiss();
                    if (task.isSuccessful()){
                        String user_id=mAuth.getCurrentUser().getUid();
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("usuarios");
                        DatabaseReference currentUserDB = mDatabase.child(mAuth.getCurrentUser().getUid());
                        currentUserDB.child("name").setValue(name);
                        currentUserDB.child("bloque").setValue(bloque.getText().toString().trim());
                        currentUserDB.child("piso").setValue(piso.getText().toString().trim());
                        currentUserDB.child("image").setValue("");

                        Toast.makeText(registerActivity.this,user_id,Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(),login.class);
                        startActivity(intent);
                    }
                    else{
                       //String email= mAuth.getCurrentUser().getEmail();
                        Toast.makeText(registerActivity.this,"no se registro",Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
