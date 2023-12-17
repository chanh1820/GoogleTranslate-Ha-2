package com.example.googletranslate.core.util;

import android.util.Log;

import com.example.googletranslate.core.dto.QuestionP4DTO;
import com.example.googletranslate.core.dto.VocabularyDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TranformerUtils {
    private String id;

    private String ExampleViet;

    private String vocabularyEng;////

    private String imageFile;

    private String soundFile;//

    private String answerA;

    private String answerB;

    private String result;/////

    private String traLoi;

    public static ArrayList<QuestionP4DTO> convertVocabulariesToQuestionP4s(List<VocabularyDTO> vocabularyDTOList){
        int index= 0;
        ArrayList<QuestionP4DTO> result = new ArrayList<>();
        for (VocabularyDTO item : vocabularyDTOList) {
            QuestionP4DTO element = new QuestionP4DTO();
            element.setImageFile(item.getImageFile());
            element.setAnswerA("   Đúng");
            element.setAnswerB("   Sai");
            element.setSoundFile(item.getSoundFile());
            element.setExampleViet(FormatterUtils.formatExampleViet(item.getWordType(), item.getExampleViet()));

            if(new Random().nextBoolean()){// kết quả đúng
                element.setSoundFile(item.getSoundFile());
                element.setVocabularyEng(item.getVocabularyEng());
                element.setResult("A");
            }else {// kết quả sai
                Integer randomNumber;
                do {
                    randomNumber = new Random().nextInt(vocabularyDTOList.size() - 1);
                    Log.e("randomNumber != index", String.valueOf(randomNumber != index));
                }while (randomNumber == index);
                VocabularyDTO randomVocabularyDTO = vocabularyDTOList.get(randomNumber);
                element.setSoundFile(randomVocabularyDTO.getSoundFile());
                element.setVocabularyEng(randomVocabularyDTO.getVocabularyEng());
                element.setResult("B");
            }
            index++;
            result.add(element);
        }

        return result;
    }
}
