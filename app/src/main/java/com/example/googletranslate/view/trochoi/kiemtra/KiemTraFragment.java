package com.example.googletranslate.view.trochoi.kiemtra;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.googletranslate.R;
import com.example.googletranslate.core.dto.QuestionP4DTO;
import com.example.googletranslate.core.util.AssetUtils;

import java.util.ArrayList;


public class KiemTraFragment extends Fragment {
    ArrayList<QuestionP4DTO> arr_Ques;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    private int mPageNumber;
    public int checkAns;

    TextView txtNum, tvViet, tvEng, tvSound;
    ImageView imvExample;
    RadioGroup radioGroup;
    RadioButton radA, radB;


    public KiemTraFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_kiem_tra, container, false);

        tvViet = rootView.findViewById(R.id.tv_kiemtra_example_viet);
        tvEng = rootView.findViewById(R.id.tv_kiemtra_example_eng);
        tvSound = rootView.findViewById(R.id.tv_kiemtra_sound);
        imvExample = rootView.findViewById(R.id.imv_kiemtra_example);
        radA = rootView.findViewById(R.id.radA);
        radB = rootView.findViewById(R.id.radB);
        radioGroup = rootView.findViewById(R.id.radGroup);


        return rootView;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arr_Ques = new ArrayList<QuestionP4DTO>();
        KiemTraActivity slidePagerActivity = (KiemTraActivity) getActivity();
        arr_Ques = slidePagerActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
        checkAns = getArguments().getInt(ARG_CHECKANSWER);
    }

    public static KiemTraFragment create(int pageNumber, int checkAnswer) {
        KiemTraFragment fragment = new KiemTraFragment();
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
        QuestionP4DTO item = arr_Ques.get(mPageNumber);
        tvViet.setText(item.getExampleViet());
        tvEng.setText(item.getVocabularyEng());


        if (item.getImageFile() == null) {
            imvExample.setVisibility(View.INVISIBLE);// ẩn ảnh
        } else {
            Drawable drawable = AssetUtils.getImageFromAsset(KiemTraActivity.unitId, item.getImageFile(), getActivity().getApplicationContext());
            if(drawable!= null){
                imvExample.setImageDrawable(drawable);
            }else {
                imvExample.setImageResource(R.drawable.question_default);
            }
        }
        radA.setText(item.getAnswerA());
        radB.setText(item.getAnswerB());

        if (checkAns != 0) {
            radA.setClickable(false);
            radB.setClickable(false);

            getCheckAns(getItem(mPageNumber).getResult(), item.getTraLoi());
        }
        tvSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssetUtils.playSoundFromAsset(KiemTraActivity.unitId, item.getSoundFile(), getContext());
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                arr_Ques.get(mPageNumber).setTraLoi(getChoiceFromId(checkedId));
            }
        });
    }

    public QuestionP4DTO getItem(int position) {
        return arr_Ques.get(position);
    }

    private String getChoiceFromId(int rabId) {
        if (rabId == R.id.radA) {// đúng
            return "A";
        } else if (rabId == R.id.radB) {// sai
            return "B";
        }else {
            Log.e("result", "null");
            return null;
        }
    }



    private void getCheckAns(String result, String ans) {
        if (result.equals(ans)) {
            if (result.equals("A")) {
                radA.setBackgroundColor(Color.GREEN);
            } else if (result.equals("B")) {
                radB.setBackgroundColor(Color.GREEN);
            } else ;
        } else if (result != ans) {
            if (result.equals("A")) {
                radA.setBackgroundColor(Color.RED);
            } else if (result.equals("B")) {
                radB.setBackgroundColor(Color.RED);
            } else ;

        }
    }
}
