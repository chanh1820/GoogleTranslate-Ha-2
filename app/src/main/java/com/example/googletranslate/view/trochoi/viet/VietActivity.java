package com.example.googletranslate.view.trochoi.viet;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.KeyConstants;
import com.example.googletranslate.core.dao.GeneralDAO;
import com.example.googletranslate.core.dto.VocabularyDTO;
import com.example.googletranslate.core.util.AssetUtils;

import java.util.ArrayList;
import java.util.List;

public class VietActivity extends AppCompatActivity {
    GeneralDAO generalDAO;
    TextView tvExampleViet, tvResult, tvNotify, tvIgnore;
    EditText edtInput;
    ImageView imgContent;
    Button btnNext, btnCheck;
    List<VocabularyDTO> listVocabulary = new ArrayList<>();
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viet);
        initView();
        action();
    }

    private void action() {
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edtInput.getText().toString().trim();
                btnNext.setVisibility(View.VISIBLE);
                edtInput.setEnabled(false);
                tvIgnore.setVisibility(View.INVISIBLE);
                tvNotify.setVisibility(View.VISIBLE);
                if(input.equals(listVocabulary.get(position).getVocabularyEng())){
                    tvNotify.setText("Chính sác");
                    tvNotify.setTextColor(Color.GREEN);
                }else{
                    tvNotify.setText("Không chính sác");
                    tvNotify.setTextColor(Color.RED);
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = position + 1;
                nextQuestion(position);
            }
        });
        tvIgnore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setVisibility(View.VISIBLE);
                tvNotify.setVisibility(View.VISIBLE);
                tvNotify.setText("Hãy nhập lại đáp án");
            }
        });
        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssetUtils.playSoundFromAsset(listVocabulary.get(position).getUnitId(),listVocabulary.get(position).getSoundFile(),getApplicationContext());
            }
        });
    }

    private void initView() {
        tvExampleViet = findViewById(R.id.tv_viet_example_viet);
        tvResult = findViewById(R.id.tv_viet_result);
        tvNotify = findViewById(R.id.tv_viet_notify);
        tvIgnore = findViewById(R.id.tv_viet_ignore);
        imgContent =findViewById(R.id.img_viet_image);
        edtInput = findViewById(R.id.edt_viet_input);
        btnCheck = findViewById(R.id.btn_viet_check);
        btnNext = findViewById(R.id.btn_viet_next);
        generalDAO = new GeneralDAO(getApplicationContext());
        listVocabulary = generalDAO.findVocabularyByUnit(getIntent().getIntExtra(KeyConstants.INTENT_UNIT_ID, 0));
        nextQuestion(position);
    }

    void nextQuestion(int position) {
        refreshUI();

        VocabularyDTO itemCurrent = listVocabulary.get(position);

        Drawable drawable = AssetUtils.getImageFromAsset(itemCurrent.getUnitId(), itemCurrent.getImageFile(), getApplicationContext());
        if(drawable!= null){
            imgContent.setImageDrawable(drawable);
        }else{
            imgContent.setImageResource(R.drawable.question_default);
        }
        tvExampleViet.setText(itemCurrent.getWordType() + ": " + itemCurrent.getVocabularyViet());

        tvResult.setText("Đáp án: " + itemCurrent.getVocabularyEng());
    }

    void refreshUI() {
        imgContent.setImageDrawable(null);
        tvExampleViet.setText("");

        tvNotify.setText("");
        tvResult.setText("");

        tvNotify.setVisibility(View.INVISIBLE);
        tvResult.setVisibility(View.GONE);

        edtInput.setText("");
        edtInput.setEnabled(true);

        btnNext.setVisibility(View.GONE);
        tvIgnore.setVisibility(View.VISIBLE);
    }
}