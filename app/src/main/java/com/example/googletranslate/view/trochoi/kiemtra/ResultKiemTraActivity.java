package com.example.googletranslate.view.trochoi.kiemtra;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.dao.GeneralDAO;
import com.example.googletranslate.core.dto.QuestionP4DTO;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class ResultKiemTraActivity extends AppCompatActivity  {
    private static float[] yData;
    TextView tvTrue, tvFasle, tvNotAns, tvTotalPoint,txtTestDone, tvNotifi;
    Button btnSave, btnAgain, btnExit;
    ImageView imvTestDone;
    ArrayList<QuestionP4DTO> arr_QuesBegin = new ArrayList<QuestionP4DTO>();
    int num_NotAns = 0;
    int num_True_Ans = 0;
    int num_Fasle_Ans = 0;
    int num_TotalPoint = 0;

    GeneralDAO generalDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_kiem_tra);

        initView();
        checkResult();
        showResult();
        Exit();
        again();
    }


    private void again() {
        btnAgain.setVisibility(View.GONE);
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                finish();
                Intent intent2= new Intent(ResultKiemTraActivity.this, KiemTraActivity.class);
                intent2.putExtra("arr_Ques",arr_QuesBegin);
                intent2.putExtra("test","no");
                startActivity(intent2);
            }

            private void refresh() {
                for(int i=0;i<arr_QuesBegin.size();i++){
                    arr_QuesBegin.get(i).setTraLoi("");
                }
            }
        });
    }

//    private void saveScore() {
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
//                LayoutInflater inflater = TestDoneActivity.this.getLayoutInflater();
//                View view=inflater.inflate(R.layout.allert_dialog_save_score,null);
//                builder.setView(view);
//
//                EditText edtName=  view.findViewById(R.id.edtName);
//                TextView txtResultNumTrue=  view.findViewById(R.id.txtResultNumTrue);
//                TextView txtResultScore=  view.findViewById(R.id.txtResultScore);
//
//                int numResultTotal= num_True_Ans;
//                String numResultTrue= String.valueOf(num_True_Ans);
//
//                edtName.setText(accountDTO.getDisplayName());
//                txtResultNumTrue.setText();
//
//                txtResultScore.setText(numResultTotal+ " Điểm");
//
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog b=builder.create();
//                b.show();
//            }
//        });
//    }

    private void Exit() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultKiemTraActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát hay không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }

    private void showResult() {
        num_TotalPoint = num_True_Ans * 1;
        tvNotAns.setText("" + num_NotAns);
        tvFasle.setText("" + num_Fasle_Ans);
        tvTrue.setText("" + num_True_Ans);
        tvTotalPoint.setText("" + num_TotalPoint);
        if(num_True_Ans >(arr_QuesBegin.size()/2)){
            tvNotifi.setText("Chúc mừng bạn đã\nvượt qua bài kiểm tra");
        }else {
            tvNotifi.setText("Bạn đã thi trượt");
        }
        yData = new float[]{num_True_Ans, num_Fasle_Ans, num_NotAns};

    }

    public void initView() {
        generalDAO= new GeneralDAO(ResultKiemTraActivity.this);
        Intent intent = getIntent();
        arr_QuesBegin = (ArrayList<QuestionP4DTO>) intent.getExtras().getSerializable("arr_Ques");
        tvFasle =  findViewById(R.id.tvFalse);
        tvTrue =  findViewById(R.id.tvTrue);
        tvNotAns =  findViewById(R.id.tvNotAns);
        tvTotalPoint =  findViewById(R.id.tvTotalPoint);
        btnAgain =  findViewById(R.id.btnAgain);
        btnSave =  findViewById(R.id.btnSaveScore);
        btnExit =  findViewById(R.id.btnExit);
        tvNotifi =  findViewById(R.id.notification_result);
    }

    public void checkResult() {
        Log.e("checkResult", arr_QuesBegin.size()+"");
        for (int i = 0; i < arr_QuesBegin.size(); i++) {
            if (StringUtils.isEmpty(arr_QuesBegin.get(i).getTraLoi()) == true) {
                num_NotAns++;
            } else if (arr_QuesBegin.get(i).getResult().equals(arr_QuesBegin.get(i).getTraLoi()) == true) {
                num_True_Ans++;
            } else num_Fasle_Ans++;
        }
    }
}