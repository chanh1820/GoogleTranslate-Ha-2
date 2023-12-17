package com.example.googletranslate.core.util;

public class FormatterUtils {

    public static String formatExampleViet (String wordType, String ExampleViet){
        String result = "{1} {2}";
        return result.replace("{1}", wordType).replace("{2}", ExampleViet);
    }
}
