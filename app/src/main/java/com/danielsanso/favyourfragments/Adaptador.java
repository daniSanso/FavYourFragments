package com.danielsanso.favyourfragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by danielsanso on 22/1/18.
 */

public class Adaptador extends BaseAdapter {

    Context contexto;
    List<DatosPerfil> ListaObjestos;//el adaptador recive el cpntexto y la lista de objetos

    public Adaptador(Context contexto, List<DatosPerfil> listaObjestos) {
        this.contexto = contexto;
        ListaObjestos = listaObjestos;
    }

    @Override
    public int getCount() {//vuelve cantidad de objetos
        return ListaObjestos.size();
    }

    @Override
    public Object getItem(int position) {//devuelve objeto de la posicion
        return ListaObjestos.get(position);
    }

    @Override
    public long getItemId(int position) {//devuelve id de la posicion indicada
        return ListaObjestos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//metodo q se ejecuta en cada secuencia en la cual se quiera cargar el contenido de cada hito del list view

        View vista =convertView;


        if(getItemViewType(position)==1){
            LayoutInflater inflate = LayoutInflater.from(contexto);//obtenemos el contexto del item sobre el q trabajamos del listView
            vista= inflate.inflate(R.layout.itemlistview,null);//obtenemos la referencia a lo q seria el poder inflar  al obj inflate q va a permitir inyectar el layout q defini en itemlistview colocarlo en listview

            ImageView imagen= (ImageView)vista.findViewById(R.id.imageView);
            TextView titulo= (TextView) vista.findViewById(R.id.txtTitulo);
            TextView detalle= (TextView) vista.findViewById(R.id.txtdetalle);

            titulo.setText(ListaObjestos.get(position).getTitulo().toString());// Referenciamos el objeto
            detalle.setText(ListaObjestos.get(position).getDetalle().toString());
            imagen.setImageResource(ListaObjestos.get(position).getImagen());

        }
        else if(getItemViewType(position)==2){
            LayoutInflater inflate = LayoutInflater.from(contexto);//obtenemos el contexto del item sobre el q trabajamos del listView
            vista= inflate.inflate(R.layout.itemlistview2,null);//obtenemos la referencia a lo q seria el poder inflar  al obj inflate q va a permitir inyectar el layout q defini en itemlistview colocarlo en listview

            TextView titulo= (TextView) vista.findViewById(R.id.txtTitulo);
            TextView detalle= (TextView) vista.findViewById(R.id.txtdetalle);

            titulo.setText(ListaObjestos.get(position).getTitulo().toString());// Referenciamos el objeto
            detalle.setText(ListaObjestos.get(position).getDetalle().toString());

        }else if(getItemViewType(position)==3){
            LayoutInflater inflate = LayoutInflater.from(contexto);//obtenemos el contexto del item sobre el q trabajamos del listView
            vista= inflate.inflate(R.layout.itemlistview3,null);//obtenemos la referencia a lo q seria el poder inflar  al obj inflate q va a permitir inyectar el layout q defini en itemlistview colocarlo en listview

            ImageView imagen= (ImageView)vista.findViewById(R.id.imageView);
            TextView titulo= (TextView) vista.findViewById(R.id.txtTitulo);
            TextView detalle= (TextView) vista.findViewById(R.id.txtdetalle);

            titulo.setText(ListaObjestos.get(position).getTitulo().toString());// Referenciamos el objeto
            detalle.setText(ListaObjestos.get(position).getDetalle().toString());
            imagen.setImageResource(ListaObjestos.get(position).getImagen());

        }



        return vista;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        DatosPerfil datosPerfil= ListaObjestos.get(position);
        if(datosPerfil.lay==3){return 3;
        }else if(datosPerfil.lay==2){return 2;

        }else {return 1;}


    }
}


