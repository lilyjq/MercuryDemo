package com.mercury.demo.singleton;

public class HungrySingleton {
    public static final HungrySingleton instance;


    public final void caculate(){

    }

    private HungrySingleton(){

    }

    static {
        instance = new HungrySingleton();
    }

}
