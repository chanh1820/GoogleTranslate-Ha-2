package com.example.googletranslate.view.dichngonngu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.DBConstant;
import com.example.googletranslate.core.constant.GoogleSheetConstant;
import com.example.googletranslate.core.dto.LanguageDTO;
import com.example.googletranslate.core.dto.TranslateSCO;
import com.example.googletranslate.core.util.ObjectMapperUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

public class DichNgonNguActivity extends AppCompatActivity {
    EditText edtInput, edtResult;
    Spinner spnChooseLanguage;
    Button btnTranslate;
    SPNChooseLanguageAdapter spnChooseLanguageAdapter;

    DichNgonNguViewDTO viewDTO = new DichNgonNguViewDTO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_ngon_ngu);
        initView();
        action();
    }

    private void action() {
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTranslate();
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
        edtInput = findViewById(R.id.edt_dich_ngon_ngu_input_word);
        edtResult = findViewById(R.id.edt_dich_ngon_ngu_result);
        spnChooseLanguage = findViewById(R.id.spn_dich_ngon_ngu_choose_language);
        btnTranslate = findViewById(R.id.btn_dich_ngon_ngu_tranlate);

        spnChooseLanguageAdapter = new SPNChooseLanguageAdapter(getApplicationContext(), DBConstant.LANGUAGE_DTO_LIST);
        spnChooseLanguage.setAdapter(spnChooseLanguageAdapter);


    }

    private void handleTranslate() {
        LanguageDTO languageDTO = viewDTO.getLanguageDTO();

        TranslateSCO translateSCO = new TranslateSCO();

        String targetlanguageCode = languageDTO.getLanguageCode();
        String input = edtInput.getText().toString().toString();

        translateSCO.setAction(GoogleSheetConstant.ACTION_DO_TRANSLATE);
        translateSCO.setSourceLang("vi");
        translateSCO.setTargetLang(targetlanguageCode);
        translateSCO.setSourceText(input);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GoogleSheetConstant.END_POINT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                edtResult.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", ObjectMapperUtils.dtoToString(error));
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return ObjectMapperUtils.dtoToMap(translateSCO, new TypeReference<Map<String, String>>() {});
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

}