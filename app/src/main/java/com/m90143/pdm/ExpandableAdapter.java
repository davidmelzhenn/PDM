package com.m90143.pdm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private List<String> listGroup;
    private HashMap<String, List<String>> listData;
    private LayoutInflater inflater;
    private List<Map<String, String>> lista;

    public ExpandableAdapter(Context context, List<String> listGroup, HashMap<String, List<String>> listData, List<Map<String, String>> lista){
        this.listGroup = listGroup;
        this.listData = listData;
        this.lista = lista;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listData.get(listGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listData.get(listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.header_expandable_list_view, null);
            holder = new ViewHolderGroup();
            convertView.setTag(holder);

            holder.tvDataHora = (TextView) convertView.findViewById(R.id.tvDataHora);
            holder.tvResultado = (TextView) convertView.findViewById(R.id.tvResultado);

        }
        else{
            holder = (ViewHolderGroup) convertView.getTag();
        }

        Map<String, String> mapa = lista.get(groupPosition);
        holder.tvDataHora.setText(mapa.get("datahora"));
        holder.tvResultado.setText(mapa.get("resultado"));

        //holder.tvDataHora.setText(listGroup.get(groupPosition));
        //holder.tvResultado.setText(listGroup.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem holder;
        String val = (String) getChild(groupPosition, childPosition);

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_expandable_list_view, null);
            holder = new ViewHolderItem();
            convertView.setTag(holder);

            holder.tvValor1 = (TextView) convertView.findViewById(R.id.tvValor1);
            holder.tvValor2 = (TextView) convertView.findViewById(R.id.tvValor2);
            holder.tvOperacao = (TextView) convertView.findViewById(R.id.tvOperacao);
        }
        else{
            holder = (ViewHolderItem) convertView.getTag();
        }

        //holder.tvValor1.setText(val);

        Map<String, String> mapa = lista.get(groupPosition);
        holder.tvValor1.setText(mapa.get("valor1"));
        holder.tvValor2.setText(mapa.get("valor2"));
        holder.tvOperacao.setText(mapa.get("operacao"));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolderGroup {
        TextView tvDataHora, tvResultado;
    }

    class ViewHolderItem {
        TextView tvValor1, tvValor2, tvOperacao;
    }

}
