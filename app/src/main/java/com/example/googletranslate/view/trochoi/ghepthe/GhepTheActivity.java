package com.example.googletranslate.view.trochoi.ghepthe;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.KeyConstants;
import com.example.googletranslate.core.dao.GeneralDAO;
import com.example.googletranslate.core.dto.CheckResultDTO;
import com.example.googletranslate.core.dto.GhepTheViewDTO;
import com.example.googletranslate.core.dto.ManhGhepDTO;
import com.example.googletranslate.core.dto.VocabularyDTO;
import com.example.googletranslate.core.util.FormatterUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GhepTheActivity extends AppCompatActivity {
    TextView tvItem1, tvItem2, tvItem3, tvItem4, tvItem5, tvItem6, tvItem7, tvItem8, tvItem9, tvItem10, tvItem11, tvItem12, tvTimer;

    GhepTheViewDTO ghepTheViewDTO = new GhepTheViewDTO();
    GeneralDAO generalDAO;


    CheckResultDTO checkResultDTO = new CheckResultDTO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghep_the);

        initView();

        action();
    }

    private void action() {
        for (TextView textView: ghepTheViewDTO.getManhGhepViewList()){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkResultDTO.getResult1()== null & checkResultDTO.getTvOne() == null){
                        checkResultDTO.setResult1(textView.getTag().toString());
                        checkResultDTO.setTvOne(textView);
                        textView.setBackgroundResource(R.drawable.style_radio_button_yellow);
                    }else {
                        checkResultDTO.setResult2(textView.getTag().toString());
                        checkResultDTO.setTvTwo(textView);
                        textView.setBackgroundResource(R.drawable.style_radio_button_yellow);

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                checkAnwer();
                            }
                        }, 500);
                    }
                }
            });

        }
    }

    private void checkAnwer() {
        if(checkResultDTO.getTvOne().getId() == checkResultDTO.getTvTwo().getId()){
            checkResultDTO.getTvOne().setBackgroundResource(R.drawable.style_radio_button_yellow);
        }else {
            if(checkResultDTO.getResult1().equals(checkResultDTO.getResult2())){
                checkResultDTO.getTvOne().setVisibility(View.INVISIBLE);
                checkResultDTO.getTvTwo().setVisibility(View.INVISIBLE);
                checkResultDTO = new CheckResultDTO();
            }else {
                checkResultDTO.getTvOne().setBackgroundResource(R.drawable.style_radio_button_white);
                checkResultDTO.getTvTwo().setBackgroundResource(R.drawable.style_radio_button_white);
                checkResultDTO = new CheckResultDTO();

            }

        }
        checkWin(ghepTheViewDTO.getManhGhepViewList());
    }

    private void initView() {
        generalDAO = new GeneralDAO(getApplicationContext());
        tvItem1 = findViewById(R.id.tv_ghep_the_1);
        tvItem2 = findViewById(R.id.tv_ghep_the_2);
        tvItem3 = findViewById(R.id.tv_ghep_the_3);
        tvItem4 = findViewById(R.id.tv_ghep_the_4);
        tvItem5 = findViewById(R.id.tv_ghep_the_5);
        tvItem6 = findViewById(R.id.tv_ghep_the_6);
        tvItem7 = findViewById(R.id.tv_ghep_the_7);
        tvItem8 = findViewById(R.id.tv_ghep_the_8);
        tvItem9 = findViewById(R.id.tv_ghep_the_9);
        tvItem10 = findViewById(R.id.tv_ghep_the_10);
        tvItem11 = findViewById(R.id.tv_ghep_the_11);
        tvItem12 = findViewById(R.id.tv_ghep_the_12);
        tvTimer = findViewById(R.id.tv_ghep_the_timer);
        List<TextView> manhGhepViewList = new ArrayList<>();
        manhGhepViewList.add(tvItem1);
        manhGhepViewList.add(tvItem2);
        manhGhepViewList.add(tvItem3);
        manhGhepViewList.add(tvItem4);
        manhGhepViewList.add(tvItem5);
        manhGhepViewList.add(tvItem6);
        manhGhepViewList.add(tvItem7);
        manhGhepViewList.add(tvItem8);
        manhGhepViewList.add(tvItem9);
        manhGhepViewList.add(tvItem10);
        manhGhepViewList.add(tvItem11);
        manhGhepViewList.add(tvItem12);

        ghepTheViewDTO.setUnitId(getIntent().getIntExtra(KeyConstants.INTENT_UNIT_ID, 0));
        ghepTheViewDTO.setManhGhepViewList(manhGhepViewList);

        List<VocabularyDTO> vocabularyDTOList = generalDAO.findVocabularyByUnit(ghepTheViewDTO.getUnitId());
        Collections.shuffle(vocabularyDTOList);

        List<ManhGhepDTO> manhGhepDTOList = new ArrayList<>();
        int maxsize = vocabularyDTOList.size() >= 6 ? 6 : vocabularyDTOList.size();
        for (int i = 0; i < maxsize; i++) {
            VocabularyDTO item = vocabularyDTOList.get(i);
            manhGhepDTOList.add(new ManhGhepDTO(item.getVocabularyEng(), item.getVocabularyEng()));
            manhGhepDTOList.add(new ManhGhepDTO(FormatterUtils.formatExampleViet(item.getWordType(), item.getVocabularyViet()), item.getVocabularyEng()));
        }
        Collections.shuffle(manhGhepDTOList);

        for (int i = 0; i < manhGhepDTOList.size() ; i++){
            ManhGhepDTO item = manhGhepDTOList.get(i);
            ghepTheViewDTO.getManhGhepViewList().get(i).setText(item.getContent());
            ghepTheViewDTO.getManhGhepViewList().get(i).setTag(item.getResult());
        }


        CountDownTimer countDownTimer  = new CountDownTimer(2* 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                tvTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
            }

            @Override
            public void onFinish() {
                handleFinish(false);
            }
        };
        countDownTimer.start();
    }

    private void handleFinish(Boolean isWin) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ghep_hinh_fishish);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        Button btnExit = dialog.findViewById(R.id.btn_dialog_ghep_hinh_exit);
        TextView tvContent = dialog.findViewById(R.id.tv_dialog_ghep_hinh_content);

        if(isWin){
            tvContent.setText("Chúc mừng bạn đã hoàn thành xuất sắc");
        }else {
            tvContent.setText("Hết thời gian");
        }

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        dialog.setCancelable(false);

        dialog.show();
    }

    void checkWin(List<TextView> textViewList){
        boolean isAllInvisible = true;
        for (TextView item : textViewList ) {
            Log.e("a",""+ item.getVisibility());
            if(item.getVisibility() != View.INVISIBLE){
                isAllInvisible = false;
            }
        }
        if(isAllInvisible){

            handleFinish(true);
        }
    }
}