package com.example.googletranslate.view.main.dich;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.DBHelper;
import com.example.googletranslate.view.dichngonngu.DichNgonNguActivity;
import com.example.googletranslate.view.dichnhieungonngu.DichNhieuNgonNguActivity;

import java.io.IOException;

public class MainDichActivity extends AppCompatActivity {
    Button btnDichNgonNgu, btnDichNhieuNgonNgu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dich);
        initView();
        action();
    }

    private void action() {
        btnDichNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDichActivity.this, DichNgonNguActivity.class);
                startActivity(intent);
            }
        });
        btnDichNhieuNgonNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDichActivity.this, DichNhieuNgonNguActivity.class);
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
        btnDichNgonNgu = findViewById(R.id.btn_dich_ngon_ngu);
        btnDichNhieuNgonNgu = findViewById(R.id.btn_dich_nhieu_ngon_ngu);
    }
}