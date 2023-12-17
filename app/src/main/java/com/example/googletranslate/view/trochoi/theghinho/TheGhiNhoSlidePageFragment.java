package com.example.googletranslate.view.trochoi.theghinho;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.googletranslate.R;
import com.example.googletranslate.core.dto.VocabularyDTO;
import com.example.googletranslate.core.util.AssetUtils;
import com.example.googletranslate.core.util.SoundUtils;

import java.util.ArrayList;
import java.util.List;


public class TheGhiNhoSlidePageFragment extends Fragment {
    List<VocabularyDTO> listVocabulary;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";

    public static final Boolean isOpen = true;
    public static final Boolean noneOpen = false;
    private int mPageNumber;
    public int checkAns;

    ImageView imvContent;
    ImageButton imbVolume;
    TextView tvExampleViet, tvVocabularyEng;
    LinearLayout lnTheGhiNhoContent,lnTheGhiNhoParent;

    public TheGhiNhoSlidePageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_the_ghi_nho_slide_page, container, false);

        imvContent = rootView.findViewById(R.id.tv_item_the_ghi_nho_image);
        imbVolume = rootView.findViewById(R.id.imb_the_ghi_nho_volume);
        tvExampleViet = rootView.findViewById(R.id.tv_item_the_ghi_nho_example_viet);
        tvVocabularyEng = rootView.findViewById(R.id.tv_item_the_ghi_nho_vocabulary_eng);
        lnTheGhiNhoContent = rootView.findViewById(R.id.ln_theghinho_content);
        lnTheGhiNhoParent = rootView.findViewById(R.id.ln_theghinho_parent);


        return rootView;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listVocabulary = new ArrayList<VocabularyDTO>();
        TheGhiNhoSlidePagerActivity slidePagerActivity = (TheGhiNhoSlidePagerActivity) getActivity();
        listVocabulary = slidePagerActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
        checkAns = getArguments().getInt(ARG_CHECKANSWER);
    }

    public static TheGhiNhoSlidePageFragment create(int pageNumber, int checkAnswer) {
        TheGhiNhoSlidePageFragment fragment = new TheGhiNhoSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(ARG_CHECKANSWER, checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        VocabularyDTO item = listVocabulary.get(mPageNumber);
        Drawable drawable = AssetUtils.getImageFromAsset(item.getUnitId(), item.getImageFile(), getContext());
        if(drawable!= null){
            imvContent.setImageDrawable(drawable);
        }else{
            imvContent.setImageResource(R.drawable.question_default);
        }
        tvVocabularyEng.setText(item.getVocabularyEng());
        tvExampleViet.setText(item.getExampleViet());
        changeVisibleContent(noneOpen);
        lnTheGhiNhoParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeVisibleContent(!(Boolean) lnTheGhiNhoParent.getTag());
            }
        });

        imbVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssetUtils.playSoundFromAsset(item.getUnitId(), item.getSoundFile(), getContext());
            }
        });
    }

    public VocabularyDTO getItem(int position) {
        return listVocabulary.get(position);
    }

    void changeVisibleContent(boolean isOpen){
        lnTheGhiNhoParent.setTag(isOpen);
        if(isOpen){
            tvVocabularyEng.setVisibility(View.GONE);
            lnTheGhiNhoContent.setVisibility(View.VISIBLE);
        }else {
            tvVocabularyEng.setVisibility(View.VISIBLE);
            lnTheGhiNhoContent.setVisibility(View.GONE);
        }
    }

    private void makeSound(String fileName) {
        SoundUtils.playAssetSound(getContext(), "unit/" + fileName.trim());
        Log.e("makeSound",fileName);
    }
}
