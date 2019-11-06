package com.m90143.pdm;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

class MeuAdapter3 extends SimpleAdapter {

    List<Map<String, String>> lista;

    public MeuAdapter3(Context ctx, List<Map<String, String>> lista, int linha, String[] strs, int[] ints) {
        super(ctx,lista,linha,strs, ints);
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        Map<String, String> mapa = lista.get(position);
        //Bitmap bmp = (Bitmap) mapa.get("img");

        TextView txtId = v.findViewById(R.id.txtId);
        TextView txtModelo = v.findViewById(R.id.txtModelo);
        TextView txtAno = v.findViewById(R.id.txtAno);
        TextView txtValor = v.findViewById(R.id.txtValor);

        return v;
    }
}