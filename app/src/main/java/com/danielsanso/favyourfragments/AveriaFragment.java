package com.danielsanso.favyourfragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class AveriaFragment extends Fragment {
  View vista;
    FloatingActionButton fab;
    Button enviarBut;
    String titulo, comentario;
    EditText edText,comText;
    int obj;

    public AveriaFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_averia, container, false);

        fab=(FloatingActionButton)vista.findViewById(R.id.floatingActionButtonCancel) ;
        enviarBut=(Button) vista.findViewById(R.id.buttonEnviar2) ;
        edText=(EditText) vista.findViewById(R.id.idLblTituloComentario1) ;
        comText=(EditText) vista.findViewById(R.id.idLblComentario1) ;

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
                DatosPerfil a= new DatosPerfil(id,titulo,comentario,2);
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









}
