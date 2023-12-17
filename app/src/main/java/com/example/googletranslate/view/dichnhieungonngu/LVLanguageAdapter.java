package com.example.googletranslate.view.dichnhieungonngu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.DBConstant;
import com.example.googletranslate.core.dto.MutilLanguageResponseDTO;

import java.util.List;

public class LVLanguageAdapter extends ArrayAdapter<MutilLanguageResponseDTO> {
    public LVLanguageAdapter(@NonNull Context context, int resource, @NonNull List<MutilLanguageResponseDTO> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_lv_language, parent, false);
        }
        TextView tvLanguage = convertView.findViewById(R.id.tv_item_lv_language_language);
        TextView tvResult = convertView.findViewById(R.id.tv_item_lv_language_result);
        ImageView imvContent = convertView.findViewById(R.id.imv_item_lv_language_content);
        MutilLanguageResponseDTO item = getItem(position);
        tvLanguage.setText(DBConstant.LANGUAGE_DTO_MAP.get(item.getLanguageCode()).getName());
        imvContent.setImageResource(DBConstant.LANGUAGE_DTO_MAP.get(item.getLanguageCode()).getDrawableId());
        tvResult.setText(item.getResult());
        return convertView;
    }
}
