package com.example.googletranslate.view.dichnhieungonngu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
import com.example.googletranslate.core.dto.MutilLanguageResponseDTO;
import com.example.googletranslate.core.dto.TranslateSCO;
import com.example.googletranslate.core.util.ObjectMapperUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DichNhieuNgonNguActivity extends AppCompatActivity {
    EditText edtInput;
    Button btnTranslate;

    ListView lvMutiResult;
    LVLanguageAdapter lvLanguageAdapter;
    List<MutilLanguageResponseDTO> mutilLanguageResponseDTOList = new ArrayList<>();

    int resourceId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_nhieu_ngon_ngu);
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
    }

    private void handleTranslate() {
        TranslateSCO translateSCO = new TranslateSCO();

        String targetLanguage = ObjectMapperUtils.dtoToString(DBConstant.LANGUAGE_KEY_LIST);
        String input = edtInput.getText().toString().toString();

        translateSCO.setAction(GoogleSheetConstant.ACTION_DO_MUTI_TRANSLATE);
        translateSCO.setSourceLang("vi");
        translateSCO.setTargetLang(targetLanguage);
        translateSCO.setSourceText(input);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, GoogleSheetConstant.END_POINT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("response", response);
                Map<String, String > result = ObjectMapperUtils.stringToTypeReference(response, new TypeReference<Map<String, String>>() { });
                mutilLanguageResponseDTOList.clear();
                for (Map.Entry<String, String> entry : result.entrySet()) {
                    MutilLanguageResponseDTO mutilLanguageResponseDTO = new MutilLanguageResponseDTO();
                    mutilLanguageResponseDTO.setLanguageCode(entry.getKey());
                    mutilLanguageResponseDTO.setResult(entry.getValue());
                    mutilLanguageResponseDTOList.add(mutilLanguageResponseDTO);
                }
                resourceId++;
                lvLanguageAdapter = new LVLanguageAdapter(getApplicationContext(), resourceId, mutilLanguageResponseDTOList);
                lvMutiResult.setAdapter(lvLanguageAdapter);
                lvLanguageAdapter.notifyDataSetChanged();
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


    private void initView() {
        edtInput = findViewById(R.id.edt_dich_nhieu_ngon_ngu_input_word);
        btnTranslate = findViewById(R.id.btn_dich_nhieu_ngon_ngu_tranlate);
        lvMutiResult = findViewById(R.id.lv_dich_nhieu_ngon_ngu);
        lvLanguageAdapter = new LVLanguageAdapter(getApplicationContext(), 0, DBConstant.EMPTY_MUTIL_LANGUAGE_RESPONSE_DTO_LIST);
        lvMutiResult.setAdapter(lvLanguageAdapter);
    }
}