package com.example.googletranslate.view.doctienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.DBConstant;
import com.example.googletranslate.core.dto.LanguageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

public class DocNgonNguActivity extends AppCompatActivity {
    Button btnSpeech;
    EditText edtInput;
    TextToSpeech textToSpeech;
    Spinner spnChooseLanguage;
    SPNChooseLanguageAdapter spnChooseLanguageAdapter;
    DocNgonNguViewDTO viewDTO = new DocNgonNguViewDTO(DBConstant.LANGUAGE_DTO_LIST.get(0));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_ngon_ngu);

        initView();
        action();
    }

    private void action() {
        btnSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toSpeak = edtInput.getText().toString();
                Locale locale = new Locale(viewDTO.getLanguageDTO().getLocaleCode());
                textToSpeech.setLanguage(locale);
                try {
                    Toast.makeText(getApplicationContext(), new ObjectMapper().writeValueAsString(textToSpeech.getAvailableLanguages()), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        spnChooseLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewDTO.setLanguageDTO((LanguageDTO) view.getTag());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView() {
        btnSpeech =findViewById(R.id.btn_doc_ngon_ngu_speech);
        edtInput =findViewById(R.id.edt_doc_ngon_ngu_input);
        spnChooseLanguage =findViewById(R.id.spn_doc_ngon_ngu_choose_language);


        spnChooseLanguageAdapter = new SPNChooseLanguageAdapter(getApplicationContext(), DBConstant.LANGUAGE_DTO_LIST);
        spnChooseLanguage.setAdapter(spnChooseLanguageAdapter);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale(viewDTO.getLanguageDTO().getLocaleCode()));
                }
            }
        }, TextToSpeech.Engine.INTENT_ACTION_TTS_SERVICE);

    }
}