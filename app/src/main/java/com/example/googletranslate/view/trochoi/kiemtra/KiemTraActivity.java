package com.example.googletranslate.view.trochoi.kiemtra;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.KeyConstants;
import com.example.googletranslate.core.dao.GeneralDAO;
import com.example.googletranslate.core.dto.QuestionP4DTO;
import com.example.googletranslate.core.dto.VocabularyDTO;
import com.example.googletranslate.core.util.TranformerUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class KiemTraActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 20;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;


    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;

    TextView tvTimer, txtKiemTra, tvXemDiem, txtCurrentPosition, tvTitle, tvBack;
    String test = "";

    LocalDateTime startTime = LocalDateTime.now();
    LocalDateTime endTime;
    GeneralDAO generalDAO;
    ArrayList<QuestionP4DTO> questionP4DTOList;
//    CounterClass timer;
    public static Integer unitId;

    Integer totalQuestion;
    Integer counterTime;
    int[] subSubject;
    public int checkAns = 0;
    String title;
    private List<VocabularyDTO> listVocabulary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_tra);
        generalDAO = new GeneralDAO(this);
        questionP4DTOList = new ArrayList<QuestionP4DTO>();
        unitId = getIntent().getIntExtra(KeyConstants.INTENT_UNIT_ID, 0);
        listVocabulary = generalDAO.findVocabularyByUnit(unitId);
        questionP4DTOList = TranformerUtils.convertVocabulariesToQuestionP4s(listVocabulary);
        Collections.shuffle(questionP4DTOList);
        totalQuestion = questionP4DTOList.size();
        Log.e("questionP4DTOList.size()",String.valueOf(questionP4DTOList.size()));


        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                txtCurrentPosition.setText(position + 1 + "/" + questionP4DTOList.size());

            }

        });


        txtKiemTra = findViewById(R.id.tvKiemTra);
        tvXemDiem = findViewById(R.id.tvScore);
        tvTimer = findViewById(R.id.tvTimer);
        tvBack = findViewById(R.id.tv_back);
        txtCurrentPosition = findViewById(R.id.txtCurentPosition);
        txtKiemTra.setVisibility(View.VISIBLE);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogExit();

            }
        });
        txtKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
        tvXemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent1 = new Intent(KiemTraActivity.this, ResultKiemTraActivity.class);
                intent1.putExtra("arr_Ques", questionP4DTOList);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            dialogExit();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public ArrayList<QuestionP4DTO> getData() {
        return questionP4DTOList;
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return KiemTraFragment.create(position, checkAns);
        }

        @Override
        public int getItemCount() {
            return totalQuestion;
        }
    }

    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

    public void checkAnswer() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_check_answer);
        dialog.setTitle("Danh sách câu trả lời");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        // truyền giá arr_Ques cho dialog
        CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter(questionP4DTOList, this);
        GridView gvLsQuestion = dialog.findViewById(R.id.gvLsQuestion);
        gvLsQuestion.setAdapter(answerAdapter);

        gvLsQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //chuyển trang đến vị trí thứ position
                viewPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        Button btnCancel, btnFinish;
        btnCancel = dialog.findViewById(R.id.btn_Cancel);
        btnFinish = dialog.findViewById(R.id.btn_Finish);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                timer.cancel();
                endTime = LocalDateTime.now();
                result();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void result() {
        checkAns = 1;// thay đổi bên fragment
        if (viewPager.getCurrentItem() >= 5) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 4);
        } else if (viewPager.getCurrentItem() <= 5) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 4);
        }
//            tvXemDiem.setVisibility(View.VISIBLE);// hiện lên
        txtKiemTra.setVisibility(View.GONE);// ẩn đi        txtKiemTra.setVisibility(View.GONE);// ẩn đi
    }

//    public class CounterClass extends CountDownTimer {
//        public CounterClass(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
//                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
//            tvTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
//        }
//
//        @Override
//        public void onFinish() {
//            tvTimer.setText("00:00");
//            Toast.makeText(KiemTraActivity.this, "Đã hết giờ làm bài", Toast.LENGTH_LONG);
//            result();
//
//        }
//    }

    public void dialogExit() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(KiemTraActivity.this);
        builder.setIcon(R.drawable.exit);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thoát hay không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                timer.cancel();
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


}