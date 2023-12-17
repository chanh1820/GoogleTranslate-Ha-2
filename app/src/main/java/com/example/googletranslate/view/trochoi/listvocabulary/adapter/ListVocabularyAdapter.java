package com.example.googletranslate.view.trochoi.listvocabulary.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googletranslate.R;
import com.example.googletranslate.core.dto.VocabularyDTO;
import com.example.googletranslate.core.util.AssetUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class ListVocabularyAdapter extends RecyclerView.Adapter<ListVocabularyAdapter.MyViewHolder> {

    Context context;
    List<VocabularyDTO> items;
    public ListVocabularyAdapter(Context context, List<VocabularyDTO> items) {
        this.context = context;
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgContent;
        TextView tvVocabularyEng, tvVocabularyViet, tvSpell, tvExampleViet, tvExampleEng, tvStt;
        LinearLayout lnParent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContent = itemView.findViewById(R.id.img_item_vocabulary_image);
            tvVocabularyEng = itemView.findViewById(R.id.tv_item_vocabulary_eng);
            tvVocabularyViet = itemView.findViewById(R.id.tv_item_vocabulary_viet);
            tvSpell = itemView.findViewById(R.id.tv_item_vocabulary_spell);
            tvExampleViet = itemView.findViewById(R.id.tv_item_vocabulary_example_viet);
            tvExampleEng = itemView.findViewById(R.id.tv_item_vocabulary_example_anh);
            tvStt = itemView.findViewById(R.id.tv_item_vocabulary_stt);
            lnParent = itemView.findViewById(R.id.ln_item_vocabulary_parent);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_vocabulary, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VocabularyDTO item = items.get(position);
        holder.tvStt.setText(String.valueOf(position + 1));
        holder.tvVocabularyEng.setText(StringUtils.capitalize(item.getVocabularyEng()));
        holder.tvSpell.setText(item.getSpell());
        holder.tvVocabularyViet.setText(item.getWordType() + ":" + item.getVocabularyViet());
        holder.tvExampleEng.setText(item.getExampleEng());
        holder.tvExampleViet.setText(item.getExampleViet());
        if (item.getImageFile() == null) {
            holder.imgContent.setVisibility(View.GONE);// ẩn ảnh
        } else {
            Drawable drawable = AssetUtils.getImageFromAsset(item.getUnitId(), item.getImageFile(), context);
            if(drawable!= null){
                holder.imgContent.setImageDrawable(drawable);
            }else{
                holder.imgContent.setImageResource(R.drawable.question_default);
            }

        }

        holder.lnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssetUtils.playSoundFromAsset(item.getUnitId(), item.getSoundFile(),context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
