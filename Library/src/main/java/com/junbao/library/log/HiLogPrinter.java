package com.junbao.library.log;

import androidx.annotation.NonNull;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 2:07 下午
 * desc   : log输出接口
 */
public interface HiLogPrinter {
    void print(@NonNull HiLogConfig config , int level , String tag , @NonNull String printString);
}
