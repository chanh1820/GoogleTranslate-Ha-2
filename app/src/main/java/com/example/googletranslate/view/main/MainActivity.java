package com.example.googletranslate.view.main;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.DBHelper;
import com.example.googletranslate.core.constant.DBConstant;
import com.example.googletranslate.core.constant.KeyConstants;
import com.example.googletranslate.core.dto.ClassRoomDTO;
import com.example.googletranslate.view.doctienganh.DocNgonNguActivity;
import com.example.googletranslate.view.main.dich.MainDichActivity;
import com.example.googletranslate.view.main.vocabulary.MainVocabularyActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnDich, btnDocTiengAnh,btnHoc;

    ChooseClassAdapter chooseClassAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        action();
        firebase();
    }

    private void action() {
        btnHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_choose_class);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setAttributes(lp);
                dialog.show();

//                chooseBookCollectionAdapter = new ChooseBookCollectionAdapter(getApplicationContext(), listBookCollectionDTO);
                chooseClassAdapter =  new ChooseClassAdapter(getApplicationContext(), DBConstant.CLASS_ROOM_DTO_LIST);
                GridView gvListSession = dialog.findViewById(R.id.gv_book_collection);
                Button btnBack = dialog.findViewById(R.id.btn_dialog_book_collection_back);

                gvListSession.setAdapter(chooseClassAdapter);
                gvListSession.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, MainVocabularyActivity.class);
                        ClassRoomDTO item = (ClassRoomDTO) view.getTag();
                        intent.putExtra(KeyConstants.INTENT_CLASS_ROOM, item.getClassCode());
                        startActivity(intent);
                    }
                });
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        btnDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainDichActivity.class);
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
    }

    private void initView() {
        DBHelper db = new DBHelper(this);
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnHoc = findViewById(R.id.btn_hoc_tap);
        btnDich = findViewById(R.id.btn_dich);
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
}