package com.m90143.pdm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

class MeuAdapter2 extends SimpleAdapter {

    List<Map<String, Object>> lista;

    public MeuAdapter2(Context ctx, List<Map<String, Object>> lista, int linha,
                       String[] strs, int[] ints) {
        super(ctx,lista,linha,strs, ints);
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        Map<String, Object> mapa = lista.get(position);
        Bitmap bmp = (Bitmap) mapa.get("img");

        ImageView img = v.findViewById(R.id.img);
        img.setImageBitmap(bmp);

        TextView txtMatricula = v.findViewById(R.id.txtMatricula);
        TextView txtNome = v.findViewById(R.id.txtNome);
        TextView txtEmail = v.findViewById(R.id.txtEmail);
        TextView txtEstado = v.findViewById(R.id.txtEstado);
        TextView txtCidade = v.findViewById(R.id.txtCidade);

        return v;
    }
}