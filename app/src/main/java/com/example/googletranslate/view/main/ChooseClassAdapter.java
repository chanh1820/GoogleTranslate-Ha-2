package com.example.googletranslate.view.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.googletranslate.R;
import com.example.googletranslate.core.dto.ClassRoomDTO;

import java.util.List;

public class ChooseClassAdapter extends ArrayAdapter<ClassRoomDTO> {

    public ChooseClassAdapter(@NonNull Context context, @NonNull List<ClassRoomDTO> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_class_room, parent, false);
        }
        TextView txtName = convertView.findViewById(R.id.tv_item_class_room_name);
        ClassRoomDTO item = getItem(position);
        convertView.setTag(item);

        txtName.setText(item.getClassName());
        return convertView;
    }
}
