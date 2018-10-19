package com.sensology.baseproject.eventBus;

import com.sensology.framelib.event.IBus;

/**
 * Created by ${chenM} on 2018/10/19.
 */
public class UserInfoEvent implements IBus.IEvent {

    private String name;

    public UserInfoEvent(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getTag() {
        return 0;
    }
}
