package com.danielsanso.favyourfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AceptarFavorActivity extends AppCompatActivity {
    ImageView imagen;
    TextView titulo,comentario;
    Button aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceptar_favor);
        imagen=(ImageView)findViewById(R.id.imagenAcept);
        titulo=(TextView) findViewById(R.id.idLblTituloFavor);
        comentario=(TextView) findViewById(R.id.idLblComentarioFavor);
        aceptar=(Button) findViewById(R.id.buttonAceptarF);





        DatosPerfil obj= (DatosPerfil) getIntent().getExtras().getSerializable("objeto123");
        comentario.setText(obj.getDetalle());
        titulo.setText(obj.getTitulo());
        imagen.setImageResource(obj.getImagen());

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }
}
