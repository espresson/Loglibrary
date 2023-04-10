package com.junbao.library.log;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 1:42 下午
 * desc   : log配置类
 */
public abstract class HiLogConfig {
    //一行最大输出长度
    static int MAX_LEN= 512;
    static HiThreadFormatter HI_THREAD_FORMATTER = new HiThreadFormatter();
    static HiStackTraceFormatter HI_STACK_TRACE_FORMATTER = new HiStackTraceFormatter();

    /**
     * 配置全局tag
     * @return
     */
    public String getGlobalTag(){
        return "HiLog";
    }

    /**
     * 配置全局启用
     * @return
     */
    public boolean enable(){
        return true;
    }

    /**
     * 打印器集合
     * @return
     */
    public HiLogPrinter[] printers() {
        return null;
    }

    /**
     * 是否包含线程信息
     * @return
     */
    public boolean includeThread(){
        return false;
    }

    /**
     * 打印堆栈信息级别 深度
     * @return
     */
    public int stackTraceDepth(){
        return 5;
    }

    /**
     * 配置Gson解析器
     * @return
     */
    public JsonParser injectJsonParser() {
        return null;
    }

    public interface JsonParser{
        String toJson(Object src);
    }

}
