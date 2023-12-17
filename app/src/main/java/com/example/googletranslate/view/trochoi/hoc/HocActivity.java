package com.example.googletranslate.view.trochoi.hoc;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.KeyConstants;
import com.example.googletranslate.core.dao.GeneralDAO;
import com.example.googletranslate.core.dto.ItemResultDTO;
import com.example.googletranslate.core.dto.VocabularyDTO;
import com.example.googletranslate.core.util.AssetUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HocActivity extends AppCompatActivity {
    GeneralDAO generalDAO;
    TextView tvExampleViet;
    RadioButton rabA, rabB, rabC, rabD;
    RadioGroup rabGroup;
    ImageView imgContent;
    Button btnNext;
    List<VocabularyDTO> listVocabulary = new ArrayList<>();
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc);
        initView();
        action();
    }

    private void action() {
        rabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btnNext.setVisibility(View.VISIBLE);

                makeColorResult(findChecked(checkedId), R.drawable.style_radius_red);
                makeColorResult(findResult(), R.drawable.style_radius_green);
                rabA.setClickable(false);
                rabB.setClickable(false);
                rabC.setClickable(false);
                rabD.setClickable(false);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = position + 1;
                nextQuestion(position);
                setVisible(View.GONE);
            }


        });
    }

    private void initView() {
        tvExampleViet = findViewById(R.id.tv_hoc_example_viet);
        rabA = findViewById(R.id.rad_hoc_a);
        rabB = findViewById(R.id.rad_hoc_b);
        rabC = findViewById(R.id.rad_hoc_c);
        rabD = findViewById(R.id.rad_hoc_d);
        rabGroup = findViewById(R.id.rad_group_hoc);
        btnNext = findViewById(R.id.btn_hoc_next);
        imgContent = findViewById(R.id.img_hoc_image);
        generalDAO = new GeneralDAO(getApplicationContext());
        listVocabulary = generalDAO.findVocabularyByUnit(getIntent().getIntExtra(KeyConstants.INTENT_UNIT_ID, 0));
        nextQuestion(position);
    }

    void nextQuestion(int position) {

        VocabularyDTO itemCurrent = listVocabulary.get(position);

        refreshUI();
        List<ItemResultDTO> listItemResult = new ArrayList<>();
        List<Integer> positions = getListRandom(0, listVocabulary.size() - 1, 4);
        Collections.shuffle(positions);
        for (Integer pos : positions) {
            Log.e("positions", pos + "");
            VocabularyDTO item = listVocabulary.get(pos);
            if (pos.equals(position)) {
                listItemResult.add(new ItemResultDTO(true, item.getVocabularyEng()));
            } else {
                listItemResult.add(new ItemResultDTO(false, item.getVocabularyEng()));
            }
        }

        Drawable drawable = AssetUtils.getImageFromAsset(itemCurrent.getUnitId(), itemCurrent.getImageFile(), getApplicationContext());
        if(drawable!= null){
            imgContent.setImageDrawable(drawable);
        }else{
            imgContent.setImageResource(R.drawable.question_default);
        }
        tvExampleViet.setText(itemCurrent.getWordType() + ": " + itemCurrent.getVocabularyViet());
        rabA.setText(listItemResult.get(0).getContent());
        rabA.setTag(listItemResult.get(0).isResult());
        rabB.setText(listItemResult.get(1).getContent());
        rabB.setTag(listItemResult.get(1).isResult());
        rabC.setText(listItemResult.get(2).getContent());
        rabC.setTag(listItemResult.get(2).isResult());
        rabD.setText(listItemResult.get(3).getContent());
        rabD.setTag(listItemResult.get(3).isResult());

    }

    List<Integer> getListRandom(int min, int max, int quantity) {
        List<Integer> result = new ArrayList<>();
        result.add(position);
        Random random = new Random();

        for (int i = 1; i < quantity; ) {
            Integer randomInt = random.nextInt(max - min + 1) + min;
            if (!result.contains(randomInt)) {
                result.add(randomInt);
                i++;
            }
        }
        return result;
    }

    void refreshUI() {

        Log.e("a","a");
        imgContent.setImageDrawable(null);
        tvExampleViet.setText("");

        this.rabGroup.clearCheck();
//        this.rabGroup.setOnCheckedChangeListener(this.);
        rabA.setClickable(true);
        rabB.setClickable(true);
        rabC.setClickable(true);
        rabD.setClickable(true);



        rabA.setBackgroundResource(R.drawable.style_radio_button_1);
        rabB.setBackgroundResource(R.drawable.style_radio_button_1);
        rabC.setBackgroundResource(R.drawable.style_radio_button_1);
        rabD.setBackgroundResource(R.drawable.style_radio_button_1);

        rabA.setText("");
        rabB.setText("");
        rabC.setText("");
        rabD.setText("");

        rabA.setTag(null);
        rabB.setTag(null);
        rabC.setTag(null);
        rabD.setTag(null);
    }

    void makeColorResult(int position, int drawable) {
        switch (position) {
            case 1:
                rabA.setBackgroundResource(drawable);
                break;
            case 2:
                rabB.setBackgroundResource(drawable);
                break;
            case 3:
                rabC.setBackgroundResource(drawable);
                break;
            case 4:
                rabD.setBackgroundResource(drawable);
                break;
            default:
        }
    }

    int findResult() {
        if ((Boolean) rabA.getTag()) {
            return 1;
        } else if ((Boolean) rabB.getTag()) {
            return 2;
        } else if ((Boolean) rabC.getTag()) {
            return 3;
        } else if ((Boolean) rabD.getTag()) {
            return 4;
        }
        return 0;
    }
    int findChecked(int checkedId) {
        if (checkedId == R.id.rad_hoc_a) {
            Log.e("checkedId1",checkedId+"");
            return 1;
        } else if (checkedId == R.id.rad_hoc_b) {
            Log.e("checkedId2",checkedId+"");
            return 2;
        } else if (checkedId == R.id.rad_hoc_c) {
            Log.e("checkedId3",checkedId+"");
            return 3;
        } else if (checkedId == R.id.rad_hoc_d) {
            Log.e("checkedId4",checkedId+"");
            return 4;
        }
        return 0;
    }
    private void setVisible(int gone) {
        btnNext.setVisibility(gone);
    }
}