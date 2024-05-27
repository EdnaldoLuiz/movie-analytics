package com.ednaldoluiz.moviedash.model.enums;

import lombok.Getter;

@Getter
public enum FileExportType {

    CSV("csv"),
    EXCEL("xlsx");

    private final String value;

    FileExportType(String value) {
        this.value = value;
    }
}
