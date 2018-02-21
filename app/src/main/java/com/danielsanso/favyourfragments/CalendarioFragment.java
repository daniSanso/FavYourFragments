package com.danielsanso.favyourfragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;


public class CalendarioFragment extends Fragment {
    View vista;
    FloatingActionButton fab;
    Button enviarBut;
    String titulo, comentario;
    EditText edText,comText;
    int obj;
    Spinner spin;
    CalendarView calendar;
    String a;
    public CalendarioFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_calendario, container, false);

        fab = (FloatingActionButton) vista.findViewById(R.id.floatingActionButtonCancel);
        enviarBut = (Button) vista.findViewById(R.id.buttonEnviar2);
        edText = (EditText) vista.findViewById(R.id.idLblTituloComentario1);
        comText = (EditText) vista.findViewById(R.id.idLblComentario1);
        calendar = (CalendarView) vista.findViewById(R.id.Cal);

       // obj = (int) getIntent().getExtras().getSerializable("objeto");

        obj=InicioFragment.Lista.size();
        fab = (FloatingActionButton) vista.findViewById(R.id.floatingActionButtonCancel);

        /*DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 1), (int) (height * 1));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);*/
calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        a = dayOfMonth+"/"+month+"/"+year;
    }
});

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
                titulo = "Reserva tenis";/*edText.getText().toString();*/
                comentario = "Hemos reservado de 7h a 10h "+a;
                int id = obj + 1;
                DatosPerfil a = new DatosPerfil(id, titulo, comentario, R.drawable.profile_img, 3);
                InicioFragment.Lista.add(a);
                synchronized (a) {
                    a.notify();
                }

                // ProfileActivity.notifyDataSetChanged();

                FragmentManager fm= getFragmentManager();
                fm.popBackStackImmediate();
            }
        });

        //llenar el spinner
       /* ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);*/

        return vista;
    }


}
