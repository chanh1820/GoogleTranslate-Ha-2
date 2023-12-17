package com.example.googletranslate.view.dichngonngu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.googletranslate.R;
import com.example.googletranslate.core.dto.LanguageDTO;

import java.util.List;

public class SPNChooseLanguageAdapter extends BaseAdapter {
    Context context;
    List<LanguageDTO> items;

    public SPNChooseLanguageAdapter(Context context, List<LanguageDTO> items) {
        this.context = context;
        this.items = items;

    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_spinner_language, viewGroup, false);
        ImageView imvContent = rootView.findViewById(R.id.imv_spn_language_image);
        TextView tvContent = rootView.findViewById(R.id.tv_spn_language_content);
        LanguageDTO item = items.get(i);
        tvContent.setText(items.get(i).getName());
        imvContent.setImageResource(item.getDrawableId());
        rootView.setTag(item);
        return rootView;
    }
}
