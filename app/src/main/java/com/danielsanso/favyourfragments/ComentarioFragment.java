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

import static com.danielsanso.favyourfragments.ProfileFragment.Listaa;


public class ComentarioFragment extends Fragment {
    FloatingActionButton fab;
    Button enviarBut;
    String titulo, comentario;
    EditText edText,comText;
    int obj;View vista;

    public ComentarioFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_comentario, container, false);

        fab=(FloatingActionButton) vista.findViewById(R.id.floatingActionButtonCancel) ;
        enviarBut=(Button)vista.findViewById(R.id.buttonEnviarw) ;
        edText=(EditText)vista.findViewById(R.id.idLblTituloComentarioo) ;
        comText=(EditText)vista.findViewById(R.id.idLblComentario) ;

        //obj= (int) getIntent().getExtras().getSerializable("objeto");
        obj= Listaa.size();
       /* DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


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

                FragmentManager fm= getFragmentManager();
                fm.popBackStackImmediate();
            }
        });

        enviarBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titulo=edText.getText().toString();
                comentario=comText.getText().toString();
                int id=obj+1;
                DatosPerfil a= new DatosPerfil(id,titulo,comentario,R.drawable.profile_img,1);
                Listaa.add(a);
                synchronized (a){
                    a.notifyAll();
                }

                // ProfileActivity.notifyDataSetChanged();

                FragmentManager fm= getFragmentManager();
                fm.popBackStackImmediate();


            }
        });


        return vista;
    }


}
