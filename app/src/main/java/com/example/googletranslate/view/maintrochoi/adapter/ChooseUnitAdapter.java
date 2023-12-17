package com.example.googletranslate.view.maintrochoi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.googletranslate.R;
import com.example.googletranslate.core.dto.UnitDTO;

import java.util.List;

public class ChooseUnitAdapter extends BaseAdapter {

    List<UnitDTO> items;
    Context context;

    public ChooseUnitAdapter(List<UnitDTO> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_main_unit, parent, false);
        UnitDTO item = (UnitDTO) getItem(position);
        TextView tvName = rootView.findViewById(R.id.tv_unit_name);
        tvName.setTag(item.getId());
        tvName.setText(item.getName());
        return rootView;
    }
}
