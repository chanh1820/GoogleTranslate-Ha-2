package com.example.googletranslate.core.dto;

import android.widget.TextView;

public class CheckResultDTO {

    private TextView tvOne;

    private String result1;

    private TextView tvTwo;

    private String result2;

    public CheckResultDTO() {
    }

    public TextView getTvOne() {
        return tvOne;
    }

    public void setTvOne(TextView tvOne) {
        this.tvOne = tvOne;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public TextView getTvTwo() {
        return tvTwo;
    }

    public void setTvTwo(TextView tvTwo) {
        this.tvTwo = tvTwo;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }
}
