package com.junbao.library.log;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 2:55 下午
 * desc   : 线程解析器
 */
public class HiThreadFormatter implements HiLogFormatter<Thread>{
    @Override
    public String format(Thread data) {
        return "Thread："+data.getName();
    }
}
