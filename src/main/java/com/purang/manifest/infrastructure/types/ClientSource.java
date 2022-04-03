package com.purang.manifest.infrastructure.types;

/**
 * 终端来源<br>
 * 0：微信端<br>
 * 1：PC端<br>
 * 2：手机端<br>
 * 3：村链<br>
 * 4：官网
 */
public enum ClientSource {

    // 微信端
    WECHAT(0),
    // pc端
    PC(1),
    // 手机端
    MOBILE(2),
    // 村链
    CHAIN(3),
    // 官网
    PORTAL(4);

    int index;

    ClientSource(int i) {
        this.index = i;
    }

    public static ClientSource of(int i) {
        ClientSource[] values = ClientSource.values();
        for (ClientSource type : values) {
            if (type.index == i) return type;
        }
        return null;
    }

    public int index() {
        return index;
    }
}
