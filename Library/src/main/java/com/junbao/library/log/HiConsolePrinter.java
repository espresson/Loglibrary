package com.junbao.library.log;

import static com.junbao.library.log.HiLogConfig.MAX_LEN;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 2:08 下午
 * desc   : 日志打印器
 */
public class HiConsolePrinter implements HiLogPrinter{

    /**
     * 分析： 获取日志长度 ，进行分行打印
     * HiLogConfig中配置下最大一行显示长度
     * @param config
     * @param level
     * @param tag
     * @param printString
     */

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        //获取日志长度
        int len = printString.length();
        //行数 = 总长度 / 一行最大长度
        int rowCount = len / MAX_LEN;
        //行数不为0时
        if (rowCount > 0 ){
            StringBuilder sb = new StringBuilder();
            int index = 0 ;
            //遍历行数
            for (int i = 0; i < rowCount; i++) {
                sb.append(printString.substring(index , index+MAX_LEN));
                index = i  + MAX_LEN;
            }
            if (index!=len){
                sb.append(printString.substring(index, len));
            }
        }
        //行数为0 ，输出剩余部分
        Log.println(level , tag , printString);
    }
}
