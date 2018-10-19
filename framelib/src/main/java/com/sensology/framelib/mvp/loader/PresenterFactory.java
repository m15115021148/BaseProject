package com.sensology.framelib.mvp.loader;

import com.sensology.framelib.mvp.IPresent;

public interface PresenterFactory<P extends IPresent> {
    P create();//create presenter
}
