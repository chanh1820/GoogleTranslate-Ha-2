package com.example.googletranslate.view.maintrochoi;

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
import com.example.googletranslate.view.maintrochoi.adapter.ChooseUnitAdapter;
import com.example.googletranslate.view.trochoi.chinhta.ChinhTaActivity;
import com.example.googletranslate.view.trochoi.ghepthe.GhepTheActivity;
import com.example.googletranslate.view.trochoi.hoc.HocActivity;
import com.example.googletranslate.view.trochoi.kiemtra.KiemTraActivity;
import com.example.googletranslate.view.trochoi.theghinho.TheGhiNhoSlidePagerActivity;
import com.example.googletranslate.view.trochoi.viet.VietActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class MainTroChoiActivity extends AppCompatActivity {
    /* view */
    Button btnTheGhiNho, btnHoc, btnViet, btnKiemTra, btnChinhTa, btnGhepThe;

    /* adapter */
    ChooseUnitAdapter chooseUnitAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trochoi);
        initView();
        action();
        firebase();
    }

    private void action() {


        btnTheGhiNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(TheGhiNhoSlidePagerActivity.class);
            }
        });
        btnHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(HocActivity.class);
            }
        });
        btnViet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(VietActivity.class);
            }
        });
        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(KiemTraActivity.class);
            }
        });
        btnChinhTa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(ChinhTaActivity.class);
            }
        });
        btnGhepThe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(GhepTheActivity.class);
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
        btnTheGhiNho = findViewById(R.id.btn_the_ghi_nho);
        btnHoc = findViewById(R.id.btn_hoc);
        btnViet =findViewById(R.id.btn_viet);
        btnKiemTra =findViewById(R.id.btn_kiem_tra);
        btnChinhTa =findViewById(R.id.btn_chinh_ta);
        btnGhepThe =findViewById(R.id.btn_ghep_the);
    }


    void showDialog(Class<?> cls){
        Dialog dialog = new Dialog(MainTroChoiActivity.this);
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
                Intent intent = new Intent(MainTroChoiActivity.this, cls);
                intent.putExtra(KeyConstants.INTENT_UNIT_ID, unitId);
                Log.e("unitId",String.valueOf(unitId));
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    private void firebase() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(DBConstant.USER_NAME, "123456")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                        }else {
                            finish();
                        }
                    }
                });
    }
}