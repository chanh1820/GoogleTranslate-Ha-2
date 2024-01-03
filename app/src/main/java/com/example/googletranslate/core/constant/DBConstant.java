package com.example.googletranslate.core.constant;

import com.example.googletranslate.R;
import com.example.googletranslate.core.dto.ClassRoomDTO;
import com.example.googletranslate.core.dto.LanguageDTO;
import com.example.googletranslate.core.dto.MutilLanguageResponseDTO;
import com.example.googletranslate.core.dto.UnitDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConstant {

    public static final List<LanguageDTO> LANGUAGE_DTO_LIST = new ArrayList<LanguageDTO>() {{
            add(new LanguageDTO(1, "Tiếng Anh", "en","en_IN", R.drawable.ic_flag_united_kingdom));
            add(new LanguageDTO(1, "Tiếng Pháp", "fr","fr_FR", R.drawable.ic_flag_france));
            add(new LanguageDTO(1, "Tiếng Đức", "de","de_DE", R.drawable.ic_flag_germany));
            add(new LanguageDTO(1, "Tiếng Nhật", "ja","ja_JP", R.drawable.ic_flag_japan));
            add(new LanguageDTO(1, "Tiếng Hàn", "ko","ko_KR", R.drawable.ic_flag_south_korea));
            add(new LanguageDTO(1, "Tiếng Nga", "ru", "ru_RU",R.drawable.ic_flag_rusia));
            add(new LanguageDTO(1, "Tiếng Trung", "zh-TW","zh_CN", R.drawable.ic_flag_china));
        }};
    public static final List<ClassRoomDTO> CLASS_ROOM_DTO_LIST = new ArrayList<ClassRoomDTO>() {{
        add(new ClassRoomDTO(1, "6", "Lớp 6"));
        add(new ClassRoomDTO(2, "7", "Lớp 7"));
        add(new ClassRoomDTO(3, "8", "Lớp 8"));
        add(new ClassRoomDTO(4, "9", "Lớp 9"));

    }};
    public static final Map<String, LanguageDTO> LANGUAGE_DTO_MAP = new HashMap<String, LanguageDTO>() {{
        for (LanguageDTO item : LANGUAGE_DTO_LIST) {
            put(item.getLanguageCode(), item);
        }
    }};

    public static final List<String> LANGUAGE_KEY_LIST = new ArrayList<String>() {{
        for (LanguageDTO item : LANGUAGE_DTO_LIST) {
            add(item.getLanguageCode());
        }
    }};

    public static final List<MutilLanguageResponseDTO> EMPTY_MUTIL_LANGUAGE_RESPONSE_DTO_LIST = new ArrayList<MutilLanguageResponseDTO>() {{
        for (LanguageDTO item : LANGUAGE_DTO_LIST) {
            MutilLanguageResponseDTO mutilLanguageResponseDTO = new MutilLanguageResponseDTO();
            mutilLanguageResponseDTO.setLanguageCode(item.getLanguageCode());
            mutilLanguageResponseDTO.setResult("");
            add(mutilLanguageResponseDTO);
        }
    }};

    public static final String URL_TRANSLATE = "https://translate.googleapis.com/translate_a/single?client=gtx&sl={from}&tl={to}&dt=t&q={content}";
    public static final String USER_FIREBASE = "sit_ha_googletranslate@gmail.com";

    public static final List<UnitDTO> listUnit = new ArrayList<UnitDTO>(){{
        add(new UnitDTO(1, "Unit 1", ""));
        add(new UnitDTO(2, "Unit 2", ""));
        add(new UnitDTO(3, "Unit 3", ""));
        add(new UnitDTO(4, "Unit 4", ""));
        add(new UnitDTO(5, "Unit 5", ""));
        add(new UnitDTO(6, "Unit 6", ""));
        add(new UnitDTO(7, "Unit 7", ""));
        add(new UnitDTO(8, "Unit 8", ""));
        add(new UnitDTO(9, "Unit 9", ""));
        add(new UnitDTO(10, "Unit 10", ""));
    }};
    public static final String FILE_MP3 = ".mp3";
    public static final String USER_NAME = "ha_vocabularyplay@gmail.com";
}
