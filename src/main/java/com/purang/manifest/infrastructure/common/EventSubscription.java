package com.purang.manifest.infrastructure.common;

public interface EventSubscription extends Runnable {
    void handle(Object data);
}
