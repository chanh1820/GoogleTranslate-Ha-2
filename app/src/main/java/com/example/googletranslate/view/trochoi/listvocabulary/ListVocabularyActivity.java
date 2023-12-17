package com.example.googletranslate.view.trochoi.listvocabulary;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googletranslate.R;
import com.example.googletranslate.core.constant.KeyConstants;
import com.example.googletranslate.core.dao.GeneralDAO;
import com.example.googletranslate.core.dto.VocabularyDTO;
import com.example.googletranslate.view.trochoi.listvocabulary.adapter.ListVocabularyAdapter;

import java.util.List;

public class ListVocabularyActivity extends AppCompatActivity {
    GeneralDAO generalDAO;
    ListVocabularyAdapter listVocabularyAdapter;
    List<VocabularyDTO> listVocabulary;

    RecyclerView rvListVocabulary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vocabulary);
        initView();
        action();
    }

    private void action() {

    }

    private void initView() {
        rvListVocabulary = findViewById(R.id.rv_list_vocabulary);
        generalDAO = new GeneralDAO(getApplicationContext());
        listVocabulary = generalDAO.findVocabularyByUnit(getIntent().getIntExtra(KeyConstants.INTENT_UNIT_ID,0));
        Log.e("log", listVocabulary.size()+"");
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListVocabulary.setLayoutManager(llm);
        listVocabularyAdapter = new ListVocabularyAdapter(ListVocabularyActivity.this, listVocabulary);
        rvListVocabulary.setAdapter(listVocabularyAdapter);
    }
}