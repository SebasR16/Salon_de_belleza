package com.example.salondebelleza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapterhistorial extends BaseAdapter {

    private Context context;
    private ArrayList<mLista> listItem;
    public Adapterhistorial(Context context, ArrayList<mLista> listItem) {
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
        view = LayoutInflater.from(context).inflate(R.layout.historial,null);
        TextView VNombre = (TextView) view.findViewById(R.id.nombreH);
        TextView Vfecha = (TextView) view.findViewById(R.id.fechaH);
        TextView VHora = (TextView) view.findViewById(R.id.horaH);
        TextView VDinero = (TextView) view.findViewById(R.id.montoH);
        System.out.println("Aqui esta el namdddddddde: "+item.getNombre());
        VNombre.setText("Nombre: "+item.getNombre());
        Vfecha.setText("Fecha: "+item.getFecha());
        VHora.setText("Hora: "+item.getHora());
        VDinero.setText("Monto total: "+item.getDinero()+" $ MXN");
        return view;
    }
}
