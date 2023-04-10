package com.junbao.library.log;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 1:42 下午
 * desc   : log类型
 */
public class HiLogType {

    @IntDef({V,D,I,W,E,A})
    //保留在源码级别
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE{}

    public final static int V = Log.VERBOSE;
    public final static int D = Log.DEBUG;
    public final static int I = Log.INFO;
    public final static int W = Log.WARN;
    public final static int E = Log.ERROR;
    public final static int A = Log.ASSERT;
}
