package com.example.googletranslate.view.main;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.DBHelper;
import com.example.googletranslate.core.constant.DBConstant;
import com.example.googletranslate.core.constant.KeyConstants;
import com.example.googletranslate.view.dichngonngu.DichNgonNguActivity;
import com.example.googletranslate.view.dichnhieungonngu.DichNhieuNgonNguActivity;
import com.example.googletranslate.view.doctienganh.DocNgonNguActivity;
import com.example.googletranslate.view.maintrochoi.MainTroChoiActivity;
import com.example.googletranslate.view.maintrochoi.adapter.ChooseUnitAdapter;
import com.example.googletranslate.view.trochoi.listvocabulary.ListVocabularyActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnDichNgonNgu, btnDichNhieuNgonNgu, btnDocTiengAnh,btnTuVung, btnTroChoi;
    ChooseUnitAdapter chooseUnitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        action();
        firebase();
    }

    private void action() {
        btnDichNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DichNgonNguActivity.class);
                startActivity(intent);
            }
        });
        btnDichNhieuNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DichNhieuNgonNguActivity.class);
                startActivity(intent);
            }
        });
        btnDocTiengAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DocNgonNguActivity.class);
                startActivity(intent);
            }
        });
        btnTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(ListVocabularyActivity.class);
            }
        });
        btnTroChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainTroChoiActivity.class);
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
        btnDichNgonNgu = findViewById(R.id.btn_dich_ngon_ngu);
        btnDichNhieuNgonNgu = findViewById(R.id.btn_dich_nhieu_ngon_ngu);
        btnDocTiengAnh = findViewById(R.id.btn_doc_tieng_anh);
    }
    private void firebase() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(DBConstant.USER_FIREBASE, "123456")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                        } else {
                            finish();
                        }
                    }
                });
    }
    void showDialog(Class<?> cls){
        Dialog dialog = new Dialog(MainActivity.this);
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
                Intent intent = new Intent(MainActivity.this, cls);
                intent.putExtra(KeyConstants.INTENT_UNIT_ID, unitId);
                Log.e("unitId",String.valueOf(unitId));
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }
}