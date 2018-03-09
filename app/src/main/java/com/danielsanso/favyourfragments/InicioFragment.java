package com.danielsanso.favyourfragments;


import android.content.Context;
import android.content.Intent;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
public class InicioFragment extends Fragment {
    View vista;
    ListView listaDatos;
    public static ArrayList<DatosPerfil> Lista;
    LinearLayout linear;

    FloatingActionButton fab, fab1, fab2, fab3;
    TextView fal1, fal2, fal3,comunidad,nombreCompleto;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
private ImageView image;

    public InicioFragment() {
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
        //  return inflater.inflate(R.layout.fragment_inicio, container, false);
        vista = inflater.inflate(R.layout.fragment_inicio, container, false);


        listaDatos = (ListView) vista.findViewById(R.id.lstDatosFavores);
        linear = (LinearLayout) vista.findViewById(R.id.idlinearlayoutu);

        fab = (FloatingActionButton) vista.findViewById(R.id.floatingActionButtton);
        fab1 = (FloatingActionButton) vista.findViewById(R.id.floatingActionButtton1);
        fab2 = (FloatingActionButton) vista.findViewById(R.id.floatingActionButtton2);
        fab3 = (FloatingActionButton) vista.findViewById(R.id.floatingActionButtton3);
        image = (ImageView) vista.findViewById(R.id.imageViewI);

        fal1 = (TextView) vista.findViewById(R.id.FloatLblPedirFavor);
        fal2 = (TextView) vista.findViewById(R.id.FloatLblInformarAveria);
        fal3 = (TextView) vista.findViewById(R.id.FloatLblReservaIns);
        comunidad = (TextView) vista.findViewById(R.id.txtcomunidadIn);
        nombreCompleto = (TextView) vista.findViewById(R.id.txtnombrecompletoIn);//mhv

        fabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward);


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
                            nombreCompleto.setText(dataSnapshot.child("name").getValue().toString());
                            comunidad.setText(dataSnapshot.child("bloque").getValue().toString()+" "+dataSnapshot.child("piso").getValue().toString());
                            String imageUrl=dataSnapshot.child("image").getValue().toString();
                            if(!imageUrl.equals("default")|| TextUtils.isEmpty(imageUrl)){
                                Context c = getActivity().getApplicationContext();
                                Picasso.with(c).load(Uri.parse(dataSnapshot.child("image").getValue().toString())).into(image);
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



        Lista = new ArrayList<DatosPerfil>();

        Lista.add(new DatosPerfil(1, "Conejo", "Disponible para tomar algo esta tarde", R.drawable.profile_img, 3));
        Lista.add(new DatosPerfil(2, "Rana", "Animal en peligro de extincion", R.drawable.profile_img, 1));
        Lista.add(new DatosPerfil(3, "Ascensor", "Puerta de ascensor averiada", 2));
        Lista.add(new DatosPerfil(5, "Pinguino", "Animal en peligro de extincion", R.drawable.profile_img, 1));
        Lista.add(new DatosPerfil(6, "Pez Payaso", "Animal en peligro de extincion", R.drawable.profile_img, 1));
        Lista.add(new DatosPerfil(7, "Caldera", "No hay agua caliente", 2));

        Lista.add(new DatosPerfil(8, "Conejo", "Animal en peligro de extincion", R.drawable.profile_img, 1));
        Lista.add(new DatosPerfil(9, "Rana", "Partido padel 19:00h falta 1 persona", R.drawable.profile_img, 3));
        Lista.add(new DatosPerfil(10, "Pez", "Animal en peligro de extincion", 2));
        Lista.add(new DatosPerfil(11, "Pinguino", "Ayudo con los niños esta tarde", R.drawable.profile_img, 1));
        Lista.add(new DatosPerfil(12, "Pez Payaso", "Animal en peligro de extincion", R.drawable.profile_img, 3));
        Lista.add(new DatosPerfil(13, "Koala", "Animal en peligro de extincion",  2));
/*
        Lista.add(new DatosPerfil(14, "Conejo", "Animal en peligro de extincion", R.drawable.profile_img, 1));
        Lista.add(new DatosPerfil(15, "Rana", "Animal en peligro de extincion", R.drawable.profile_img, 1));
        Lista.add(new DatosPerfil(16, "Pez", "Animal en peligro de extincion", R.drawable.profile_img, 1));
        Lista.add(new DatosPerfil(17, "Pinguino", "Animal en peligro de extincion", 2));
        Lista.add(new DatosPerfil(18, "Pez Payaso", "Animal en peligro de extincion", R.drawable.profile_img, 1));
        Lista.add(new DatosPerfil(19, "Koala", "Animal en peligro de extincion", R.drawable.profile_img, 1));
*/
        final Adaptador miadaptador = new Adaptador(getContext(), Lista);
        listaDatos.setAdapter(miadaptador);

       listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {//new O + enter en el parentesis
            @Override                                                           //obtener el onjeto seleccionado
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatosPerfil obj =(DatosPerfil) parent.getItemAtPosition(position);//retorna el onbj de la posicion indicada
    //animacion de la caja.
                /*Intent paso = new Intent(getActivity().getApplicationContext(),AceptarFavorActivity.class);
                paso.putExtra("objeto123",(Serializable) obj); //guardamos el obj con la clave objeto
                //cuando trabajamos con clases, para pasar datos lo mejopr es
                //serializar el obj complerto, cargarlo dentro de la intencion y pasarlo a la segunda pantala
                startActivity(paso);*/

            }
        });

       /* listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {//new O + enter en el parentesis
            @Override                                                           //obtener el onjeto seleccionado
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "asdfghjjk" , Toast.LENGTH_SHORT).show();

                DatosPerfil obj =(DatosPerfil) parent.getItemAtPosition(position);//retorna el onbj de la posicion indicada


                Intent paso = new Intent(getActivity().getApplicationContext(),AceptarFavorActivity.class);
                paso.putExtra("objeto123",(Serializable) obj); //guardamos el obj con la clave objeto
                //cuando trabajamos con clases, para pasar datos lo mejopr es
                //serializar el obj complerto, cargarlo dentro de la intencion y pasarlo a la segunda pantala
                startActivity(paso);
            }
        });*/

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent= new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);*/
                ProfileFragment profileFragment = new ProfileFragment();
                FragmentManager fm= getFragmentManager();
                fm.beginTransaction().add(
                        R.id.constLay4Fragment,
                        profileFragment,
                        profileFragment.getTag()
                ).addToBackStack("ssss").commit();

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {//boton mas
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });
        //segundos botones del menufloatingbutton desde aqui
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//boton favor
                //codigo floatboton1
                //Toast.makeText(MainActivity.this, "Primer boton presionado", Toast.LENGTH_SHORT).show();

                FavorFragment favorFragment = new FavorFragment();
                FragmentManager fm= getFragmentManager();
                fm.beginTransaction().add(
                        R.id.constLay4Fragment,
                        favorFragment,
                        favorFragment.getTag()
                ).addToBackStack("s").commit();
                /*Intent intent = new Intent(getContext(), FavorActivity.class);
                int obj = (int) Lista.size();
                intent.putExtra("objeto", (Serializable) obj); //guardamos el obj con la clave objeto
                startActivity(intent);*/
                /*para cerrar el menu floating*/

                animateFab();
                //Fin cierre fab
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo floatboton2
                //Toast.makeText(Main.this, "Segundo boton presionado", Toast.LENGTH_SHORT).show();
                AveriaFragment averiaFragment  = new AveriaFragment();
                FragmentManager fm= getFragmentManager();
                fm.beginTransaction().add(
                        R.id.constLay4Fragment,
                        averiaFragment,
                        averiaFragment.getTag()
                ).addToBackStack("ss").commit();

               /* Intent intent = new Intent(getContext(), AveriaActivity.class);
                int obj = (int) Lista.size();
                intent.putExtra("objeto", (Serializable) obj); //guardamos el obj con la clave objeto
                startActivity(intent);*/
                /*para cerrar el menu floating*/

                animateFab();
                //Fin cierre fab

            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo floatboton3
               // Toast.makeText(Main.this, "Tercer boton presionado", Toast.LENGTH_SHORT).show();

                CalendarioFragment calendarioFragment  = new CalendarioFragment();
                FragmentManager fm= getFragmentManager();
                fm.beginTransaction().add(
                        R.id.constLay4Fragment,
                        calendarioFragment,
                        calendarioFragment.getTag()
                ).addToBackStack("sss").commit();

                /*
                Intent intent = new Intent(getContext(), CalendarioActivity.class);
                startActivity(intent);*/
                /*para cerrar el menu floating*/

                animateFab();
                //Fin cierre fab

            }
        });

        listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int mSelectedItem = position;
                miadaptador.notifyDataSetChanged();

            }

        });
        return vista;
    }


    private  void animateFab(){
        if(isOpen){
            isOpen=false;
            fab.startAnimation(rotateBackward);

            fab1.startAnimation(fabClose);
            fab2.startAnimation(fabClose);
            fab3.startAnimation(fabClose);
            fal1.startAnimation(fabClose);
            fal2.startAnimation(fabClose);
            fal3.startAnimation(fabClose);

            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);


        }
        else{
            isOpen=true;

            fab.startAnimation(rotateForward);

            fab1.startAnimation(fabOpen);
            fab2.startAnimation(fabOpen);
            fab3.startAnimation(fabOpen);
            fal1.startAnimation(fabOpen);
            fal2.startAnimation(fabOpen);
            fal3.startAnimation(fabOpen);

            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);

        }

    }


}
