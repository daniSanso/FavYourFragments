package com.danielsanso.favyourfragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.pm.PackageManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
View vista;
    ImageView imagen;
    FloatingActionButton buttonFloating;
    TextView backb,txtlike,txtunlike,numlik,numunlik,nombre,piso;
    LinearLayout butlike,butunlike;
    boolean clck=false;
    boolean clck2=false;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;



    int likea, unlikea;
    private static final int CAMERA_REQUEST_CODE = 0;

    ListView listaDatos;
    public static ArrayList<DatosPerfil> Listaa;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_profile, container, false);
        EditText back=null;

        //  back=(EditText) findViewById(R.id.backid);
        imagen=(ImageView)vista.findViewById(R.id.idimagen);
        backb=(TextView)vista.findViewById(R.id.backid);
        txtlike=(TextView)vista.findViewById(R.id.textlike);
        txtunlike=(TextView)vista.findViewById(R.id.textunlike);
        nombre=(TextView)vista.findViewById(R.id.textViewNombreP);
        piso=(TextView)vista.findViewById(R.id.textViewPisoP);
        butlike=(LinearLayout) vista.findViewById(R.id.clicklike);
        butunlike=(LinearLayout)vista.findViewById(R.id.clickunlike);
        numlik=(TextView)vista.findViewById(R.id.numlike1) ;
        numunlik=(TextView)vista.findViewById(R.id.numunlike1) ;

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                if(intent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(Intent.createChooser(intent,"select a picture for your profile"),CAMERA_REQUEST_CODE);
                }
            }
        });

        mAuth=FirebaseAuth.getInstance();
        mAuthListener=(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    mStorage= FirebaseStorage.getInstance().getReference();
                    mDatabase= FirebaseDatabase.getInstance().getReference().child("usuarios");
                    mDatabase.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            nombre.setText(dataSnapshot.child("name").getValue().toString());
                            piso.setText(dataSnapshot.child("bloque").getValue().toString()+" "+dataSnapshot.child("piso").getValue().toString());
                            String imageUrl=dataSnapshot.child("image").getValue().toString();
                            if(!imageUrl.equals("default")|| TextUtils.isEmpty(imageUrl)){
                                Context c = getActivity().getApplicationContext();
                                Picasso.with(c).load(Uri.parse(dataSnapshot.child("image").getValue().toString())).into(imagen);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    Intent intent= new Intent(getActivity().getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });




        buttonFloating=(FloatingActionButton) vista.findViewById(R.id.floatingActionButton5);

        listaDatos=(ListView) vista.findViewById(R.id.lstDatosComentario);


        Listaa= new ArrayList<DatosPerfil>();

        Listaa.add(new DatosPerfil(1,"Conejo","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(2,"Rana","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(3,"Pez","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(4,"Pez","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(5,"Pinguino","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(6,"Pez Payaso","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(7,"Koala","Animal en peligro de extincion",R.drawable.profile_img,1));

        Listaa.add(new DatosPerfil(8,"Conejo","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(9,"Rana","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(10,"Pez","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(11,"Pinguino","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(12,"Pez Payaso","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(13,"Koala","Animal en peligro de extincion",R.drawable.profile_img,1));


        Listaa.add(new DatosPerfil(14,"Conejo","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(15,"Rana","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(16,"Pez","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(17,"Pinguino","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(18,"Pez Payaso","Animal en peligro de extincion",R.drawable.profile_img,1));
        Listaa.add(new DatosPerfil(19,"Koala","Animal en peligro de extincion",R.drawable.profile_img,1));

        Adaptador miadaptador= new Adaptador(getContext(),Listaa);
        listaDatos.setAdapter(miadaptador);

       /* listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {//new O + enter en el parentesis
            @Override                                                           //obtener el onjeto seleccionado
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datos obj =(Datos) parent.getItemAtPosition(position);//retorna el onbj de la posicion indicada

                Intent paso = new Intent(getApplicationContext(),DetalleActivity.class);
                paso.putExtra("objeto",(Serializable) obj); //guardamos el obj con la clave objeto
                //cuando trabajamos con clases, para pasar datos lo mejopr es
                //serializar el obj complerto, cargarlo dentro de la intencion y pasarlo a la segunda pantala
                startActivity(paso);
            }
        });*/



      /* imagen.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });*/
        buttonFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int obj =(int) Listaa.size();

                ComentarioFragment comentarioFragment = new ComentarioFragment();
                FragmentManager fm= getFragmentManager();
                fm.beginTransaction().add(
                        R.id.constLay4Fragment,
                        comentarioFragment,
                        comentarioFragment.getTag()
                ).addToBackStack("a").commit();
               /* Intent intent= new Intent(getContext(),comentarioActivity.class);
                intent.putExtra("objeto",(Serializable) obj); //guardamos el obj con la clave objeto
                //cuando trabajamos con clases, para pasar datos lo mejopr es
                //serializar el obj complerto, cargarlo dentro de la intencion y pasarlo a la segunda pantala
                startActivity(intent);*/
            }
        });
        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm= getFragmentManager();

                fm.popBackStackImmediate();
            }
        });



        butlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clck2==true){unlikea=Integer.parseInt(numunlik.getText().toString());
                    unlikea--;
                    numunlik.setText(String.valueOf(unlikea));
                    Drawable img = getResources().getDrawable(R.drawable.ic_thumb_down);

                    txtunlike.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                    clck2=false;

                }

                if(clck==false) {
                    likea=Integer.parseInt(numlik.getText().toString());
                    likea++;
                    numlik.setText(String.valueOf(likea));
                    Drawable img = getResources().getDrawable(R.drawable.ic_thumb_up2);
                    txtlike.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                    clck=true;
                }
                else {Drawable img = getResources().getDrawable(R.drawable.ic_thumb_up);
                    likea=Integer.parseInt(numlik.getText().toString());
                    likea--;
                    numlik.setText(String.valueOf(likea));
                    txtlike.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                    clck=false;

                }
            }
        });
        butunlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clck==true){
                    likea=Integer.parseInt(numlik.getText().toString());
                    likea--;
                    numlik.setText(String.valueOf(likea));
                    Drawable img = getResources().getDrawable(R.drawable.ic_thumb_up);
                    txtlike.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                    clck=false;


                }
                if(clck2==false) {
                    unlikea=Integer.parseInt(numunlik.getText().toString());
                    unlikea++;
                    numunlik.setText(String.valueOf(unlikea));
                    Drawable img = getResources().getDrawable(R.drawable.ic_thumb_down2);
                    txtunlike.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                    clck2=true;
                }
                else {Drawable img = getResources().getDrawable(R.drawable.ic_thumb_down);
                    unlikea=Integer.parseInt(numunlik.getText().toString());
                    unlikea--;
                    numunlik.setText(String.valueOf(unlikea));
                    txtunlike.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
                    clck2=false;

                }

            }
        });

        return vista;
    }

}
