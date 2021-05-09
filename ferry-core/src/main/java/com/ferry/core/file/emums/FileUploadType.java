package com.ferry.core.file.emums;


public enum FileUploadType {
    COMMON("FerryMan/"),
    QRCODE("FerryMan/qrcode/"),
    SIMPLE("FerryMan/article/"),
    COVER_IMAGE("FerryMan/cover/");

    private String path;

    FileUploadType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
