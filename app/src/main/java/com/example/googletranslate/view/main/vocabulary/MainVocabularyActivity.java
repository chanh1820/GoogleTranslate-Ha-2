package com.example.googletranslate.view.main.vocabulary;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.DBHelper;
import com.example.googletranslate.core.constant.DBConstant;
import com.example.googletranslate.core.constant.KeyConstants;
import com.example.googletranslate.view.maintrochoi.MainTroChoiActivity;
import com.example.googletranslate.view.maintrochoi.adapter.ChooseUnitAdapter;
import com.example.googletranslate.view.trochoi.listvocabulary.ListVocabularyActivity;

import java.io.IOException;

public class MainVocabularyActivity extends AppCompatActivity {
    Button btnTuVung, btnTroChoi;
    ChooseUnitAdapter chooseUnitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vocabulary);
        initView();
        action();
    }

    private void action() {
        btnTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(ListVocabularyActivity.class);
            }
        });
        btnTroChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainVocabularyActivity.this, MainTroChoiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        DBHelper db = new DBHelper(this);
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnTuVung = findViewById(R.id.btn_tu_vung);
        btnTroChoi = findViewById(R.id.btn_tro_choi);
    }
    void showDialog(Class<?> cls){
        Dialog dialog = new Dialog(MainVocabularyActivity.this);
        dialog.setContentView(R.layout.dialog_main_choose_unit);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setAttributes(lp);

        dialog.show();

        chooseUnitAdapter = new ChooseUnitAdapter(DBConstant.listUnit, getApplicationContext());
        GridView gvListSession = dialog.findViewById(R.id.gv_list_session);
        gvListSession.setAdapter(chooseUnitAdapter);
        gvListSession.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout parentView = (LinearLayout) view;
                Integer unitId = (Integer) parentView.getChildAt(0).getTag();
                Intent intent = new Intent(MainVocabularyActivity.this, cls);
                intent.putExtra(KeyConstants.INTENT_UNIT_ID, unitId);
                Log.e("unitId",String.valueOf(unitId));
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }
}