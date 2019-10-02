package com.m90143.pdm;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

class MeuAdapter extends SimpleAdapter {

    public MeuAdapter(Context ctx, List<Map<String, String>> lista, int linha, String[] strs, int[] ints) {
        super(ctx,lista,linha,strs, ints);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        ImageView img = v.findViewById(R.id.img);
        TextView tv1 = v.findViewById(R.id.txt1);
        TextView tv2 = v.findViewById(R.id.txt2);


        if (position < 4){
            v.setBackgroundColor(Color.YELLOW);
            tv1.setTextColor(Color.BLACK);
            tv2.setTextColor(Color.BLACK);
        }
        else{
            if (position %2 == 0) {
                tv1.setTextColor(Color.BLACK);
                tv2.setTextColor(Color.BLACK);
                v.setBackgroundColor(Color.WHITE);
            }
            else {
                tv1.setTextColor(Color.WHITE);
                tv2.setTextColor(Color.WHITE);
                v.setBackgroundColor(Color.BLACK);
            }
        }



        return v;
    }
}