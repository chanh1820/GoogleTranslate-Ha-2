package com.example.googletranslate.view.trochoi.theghinho;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
import com.example.googletranslate.core.dto.VocabularyDTO;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TheGhiNhoSlidePagerActivity extends FragmentActivity {
    private static final int NUM_PAGES = 20;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    TextView tvPositon, tvBack, tvNext;
    GeneralDAO generalDAO;
    List<VocabularyDTO> listVocabulary;
    public int checkAns = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_ghi_nho_slide_pager);
        initView();
        action();
    }

    private void action() {

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tvPositon.setText(position + 1 + "/"+ listVocabulary.size());
                if (position == 0) {
                    tvBack.setVisibility(View.INVISIBLE);
                    tvNext.setVisibility(View.VISIBLE);
                } else if (position == listVocabulary.size() - 1) {
                    tvBack.setVisibility(View.VISIBLE);
                    tvNext.setVisibility(View.INVISIBLE);
                } else {
                    tvBack.setVisibility(View.VISIBLE);
                    tvNext.setVisibility(View.VISIBLE);
                }
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
    }

    private void initView() {
        generalDAO = new GeneralDAO(this);
        listVocabulary = generalDAO.findVocabularyByUnit(getIntent().getIntExtra(KeyConstants.INTENT_UNIT_ID, 0));
        viewPager = findViewById(R.id.vp2_the_ghi_nho);
        tvPositon = findViewById(R.id.tv_item_the_ghi_nho_position);
        tvNext = findViewById(R.id.tv_item_the_ghi_nho_next);
        tvBack = findViewById(R.id.tv_item_the_ghi_nho_back);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public List<VocabularyDTO> getData() {
        return listVocabulary;
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return TheGhiNhoSlidePageFragment.create(position, checkAns);
        }

        @Override
        public int getItemCount() {
            return listVocabulary.size();
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
}