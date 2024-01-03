package com.example.googletranslate.view.phanhoi;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.KeyConstants;


public class PhanHoiActivity extends AppCompatActivity {
    WebView wvReadBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_hoi);
        initView();
    }

    private void initView() {
        wvReadBook = findViewById(R.id.wv_read_book);
//        wvReadBook.loadUrl(getIntent().getStringExtra(KeyConstants.INTENT_WEB_VIEW_LINK));
        wvReadBook.setWebChromeClient(new WebChromeClient());
        wvReadBook.getSettings().setJavaScriptEnabled(true);
        wvReadBook.getSettings().setLoadWithOverviewMode(true);
        wvReadBook.getSettings().setUseWideViewPort(true);
        wvReadBook.getSettings().setDomStorageEnabled(true);

        wvReadBook.loadUrl(getIntent().getStringExtra(KeyConstants.INTENT_LINK));
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(getIntent().getStringExtra(KeyConstants.INTENT_WEB_VIEW_LINK)));
//        startActivity(i);
    }
}