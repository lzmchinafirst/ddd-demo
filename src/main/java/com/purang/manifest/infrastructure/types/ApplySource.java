package com.purang.manifest.infrastructure.types;

/**
 * 申报来源<br>
 * 0：企业申报<br>
 * 1：经纪人申报<br>
 */
public enum ApplySource {

    // 企业申报
    COMPANY(0),
    // 经纪人申报
    BROKER(1);

    int index;

    ApplySource(int i) {
        this.index = i;
    }

    public static ApplySource of(int i) {
        ApplySource[] values = ApplySource.values();
        for (ApplySource type : values) {
            if (type.index == i) return type;
        }
        return null;
    }

    public int index() {
        return index;
    }
}
