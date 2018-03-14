package com.danielsanso.favyourfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.danielsanso.favyourfragments.tipos.Favor;
import com.danielsanso.favyourfragments.tipos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavorFragment extends Fragment {
    View vista;
    FloatingActionButton fab;
    Button enviarBut;
    String titulo, comentario;
    EditText edText,comText;
    int obj;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    private FirebaseAuth.AuthStateListener mAuthListener;



    public FavorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_favor, container, false);

        fab=(FloatingActionButton)vista.findViewById(R.id.floatingActionButtonCancel) ;
        enviarBut=(Button) vista.findViewById(R.id.buttonEnviar2) ;
        edText=(EditText) vista.findViewById(R.id.idLblTituloComentario1) ;
        comText=(EditText) vista.findViewById(R.id.idLblComentario1) ;
        mAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        //obj= (int) getIntent().getExtras().getSerializable("objeto");
        obj=(int)InicioFragment.Lista.size();

        DisplayMetrics dm = new DisplayMetrics();
       /* getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*1),(int)(height*1));

        WindowManager.LayoutParams params= getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;

        getWindow().setAttributes(params);*/
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    /*Intent intent= new Intent(getActivity().getApplicationContext(),MainActivity.class);
                    startActivity(intent);*/
                }
            }
        };

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ProfileActivity.notifyDataSetChanged();
                FragmentManager fm= getFragmentManager();
                fm.popBackStackImmediate();
                       /* R.id.constLay4Fragment,
                        this
                )*/

                //finish();
            }
        });
        enviarBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo=edText.getText().toString();
                comentario=comText.getText().toString();
                int id=obj+1;
                DatosPerfil a= new DatosPerfil(id,titulo,comentario,R.drawable.profile_img,1);
                startRegister();
                InicioFragment.Lista.add(a);
                synchronized (a){
                    a.notify();
                }

                // ProfileActivity.notifyDataSetChanged();
                FragmentManager fm= getFragmentManager();
                fm.popBackStackImmediate();
                //finish();
            }
        });


        // Inflate the layout for this fragment
        return vista;
    }

    private void startRegister() {
        final String namme=edText.getText().toString().trim();
        final String maiil=comText.getText().toString().trim();
        //final Usuario password= (FirebaseDatabase.getInstance().getReference().getRef().toString(),namme,maiil);

       /* Favor f = new Favor(name, mail,(password));

        mDatabase.child("usuarios").child(mAuth.getCurrentUser().getUid()).setValue(f);

        if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(password)&&!TextUtils.isEmpty(mail)){

            mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String user_id=mAuth.getCurrentUser().getUid();
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("FavoresComun");
                        DatabaseReference currentUserDB = mDatabase.child(mAuth.getCurrentUser().getUid());
                        currentUserDB.child("titulo").setValue(name);
                        currentUserDB.child("comentario").setValue(mail);
                        currentUserDB.child("usuario").setValue(password);
                        //currentUserDB.child("image").setValue(mAuth.getCurrentUser().getPhotoUrl());

                        Toast.makeText(getActivity(),"favor creado  \n"+currentUserDB.child("titulo").toString().trim(),Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getActivity().getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                    else{
                        //String email= mAuth.getCurrentUser().getEmail();
                        Toast.makeText(getActivity().getApplicationContext(),"no se envio el favor",Toast.LENGTH_SHORT).show();

                    }
                }
            });


        }*/
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

}
