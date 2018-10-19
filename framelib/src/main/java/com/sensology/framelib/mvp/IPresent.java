package com.sensology.framelib.mvp;

public interface IPresent<V> {
    void attachV(V view);

    void detachV();

    boolean hasV();
}
