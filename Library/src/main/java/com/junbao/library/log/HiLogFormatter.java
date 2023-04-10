package com.junbao.library.log;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 2:54 下午
 * desc   : 日志解析器
 */
public interface HiLogFormatter <T>{

    String format(T data);
}
