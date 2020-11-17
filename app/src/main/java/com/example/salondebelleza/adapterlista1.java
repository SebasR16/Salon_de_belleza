package com.example.salondebelleza;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapterlista1 extends BaseAdapter {

    private Context context;
    private ArrayList<mLista> listItem;

    public adapterlista1(Context context, ArrayList<mLista> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int posicion) {
        return listItem.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return 0;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        mLista item = (mLista) getItem(posicion);
        view = LayoutInflater.from(context).inflate(R.layout.vercitas,null);
        TextView VNombre = (TextView) view.findViewById(R.id.nombre);
        TextView Vfecha = (TextView) view.findViewById(R.id.Fecha);
        TextView VHora = (TextView) view.findViewById(R.id.hora);
        System.out.println("Aqui esta el name: "+item.getNombre());
        VNombre.setText(item.getNombre());
        Vfecha.setText(item.getFecha());
        VHora.setText(item.getHora());
        return view;
    }


}
