package com.example.googletranslate.view.trochoi.chinhta;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.KeyConstants;
import com.example.googletranslate.core.dao.GeneralDAO;
import com.example.googletranslate.core.dto.ChinhTaViewDTO;
import com.example.googletranslate.core.dto.VocabularyDTO;
import com.example.googletranslate.core.util.AssetUtils;
import com.example.googletranslate.core.util.FormatterUtils;

import java.util.Collections;
import java.util.List;

public class ChinhTaActivity extends AppCompatActivity {
    TextView tvIndex, tvSuggestContent;
    EditText edtInput;
    ImageView imvVolume, imvSuggestImage;
    Button btnCheck;

    GeneralDAO generalDAO;
    List<VocabularyDTO> vocabularyDTOList;

    ChinhTaViewDTO chinhTaViewDTO = new ChinhTaViewDTO(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_ta);
        initView();
        action();
    }

    private void action() {
        imvVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AssetUtils.playSoundFromAsset(
                        chinhTaViewDTO.getUnitId(),
                        chinhTaViewDTO.getVocabularyVO().getSoundFile(),
                        getApplicationContext()
                );
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtInput.getText().toString().trim().isEmpty()){
                    handleCheckResult(edtInput.getText().toString().trim());
                    edtInput.getText().clear();
                }
            }
        });
    }

    private void initView() {
        generalDAO = new GeneralDAO(getApplicationContext());
        tvIndex = findViewById(R.id.tv_chinh_ta_index);
        tvSuggestContent = findViewById(R.id.tv_chinh_ta_suggest_content);
        edtInput = findViewById(R.id.edt_chinh_ta_input);
        imvVolume = findViewById(R.id.imv_chinh_ta_volume);
        imvSuggestImage = findViewById(R.id.imv_chinh_ta_suggest_image);
        btnCheck = findViewById(R.id.btn_chinh_ta_check);
        vocabularyDTOList = generalDAO.findVocabularyByUnit(getIntent().getIntExtra(KeyConstants.INTENT_UNIT_ID, 0));
        chinhTaViewDTO.setUnitId(getIntent().getIntExtra(KeyConstants.INTENT_UNIT_ID, 0));
        chinhTaViewDTO.setListSize(vocabularyDTOList.size());
        Collections.shuffle(vocabularyDTOList);
        updateView();
    }

    private void updateView() {
        VocabularyDTO item = vocabularyDTOList.get(chinhTaViewDTO.getIndex());
        chinhTaViewDTO.setVocabularyVO(item);
        tvIndex.setText( (chinhTaViewDTO.getIndex()+1) +"/" + chinhTaViewDTO.getListSize());
        tvSuggestContent.setText(FormatterUtils.formatExampleViet(item.getWordType(), item.getExampleViet()));
        Drawable drawable = AssetUtils.getImageFromAsset(item.getUnitId(), item.getImageFile(), getApplicationContext());
        if(drawable!= null){
            imvSuggestImage.setImageDrawable(drawable);
        }else{
            imvSuggestImage.setImageResource(R.drawable.question_default);
        }
        
        
    }

    private void handleCheckResult(String yourResult) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_chinh_ta_result);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        Button btnNext = dialog.findViewById(R.id.btn_dialog_chinh_ta_next);
        Button btnResume = dialog.findViewById(R.id.btn_dialog_chinh_ta_resume);

        btnNext.setVisibility(View.GONE);
        btnResume.setVisibility(View.GONE);

        TextView tvYourResult = dialog.findViewById(R.id.tv_dialog_chinh_ta_your_result);
        TextView tvSystemResult = dialog.findViewById(R.id.tv_dialog_chinh_ta_system_result);

        tvYourResult.setText(yourResult);
        tvSystemResult.setText(chinhTaViewDTO.getVocabularyVO().getVocabularyEng());

        if(yourResult.equalsIgnoreCase(chinhTaViewDTO.getVocabularyVO().getVocabularyEng())){
            btnNext.setVisibility(View.VISIBLE);
            tvYourResult.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check, 0);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    nextQuestion();
                }
            });
        }else {
            btnResume.setVisibility(View.VISIBLE);
            tvYourResult.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0);
            btnResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }



        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(false);

        dialog.show();
    }

    private void nextQuestion() {
        chinhTaViewDTO.increaseIndex();
        updateView();
    }

}