package com.example.demo.enums;


public enum FileTypeEnum {

    IMG(1, "图片", ".jpg"),
    AUDIO(2, "音频", ".mp3"),
    VIDEO(3, "视频", ".mp4"),
    APP(4, "App包", ".apk"),
    OTHER(5, "其他", "");

    private Integer code;
    private String message;
    private String defaultSuffix;

    FileTypeEnum(Integer code, String message, String defaultSuffix) {
        this.code = code;
        this.message = message;
        this.defaultSuffix = defaultSuffix;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDefaultSuffix() {
        return defaultSuffix;
    }

    public static String fromCode(Integer code) {
        for (FileTypeEnum enums : FileTypeEnum.values()) {
            if (enums.getCode().equals(code)) {
                return enums.getDefaultSuffix();
            }
        }
        return "";
    }

}


