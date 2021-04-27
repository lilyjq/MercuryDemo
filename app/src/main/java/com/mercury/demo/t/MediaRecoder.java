package com.mercury.demo.t;
//javac F:\test\MercuryDemo\app\src\main\java\com\mercury\demo\t\MediaRecoder.java
//cd  F:\test\MercuryDemo\app\src\main\java
//javah com.mercury.demo.t.MediaRecoder
public class MediaRecoder {
    static {
        System.loadLibrary("media_jni");
        native_init();
    }
    private static native final  void native_init();
    public native  void start() throws IllegalStateException;
}
