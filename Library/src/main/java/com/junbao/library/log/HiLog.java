package com.junbao.library.log;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 1:41 下午
 * desc   : Log
 */
public class HiLog {
    private static final String HI_LOG_PACKAGE;

    static {
        String className = HiLog.class.getName();
        HI_LOG_PACKAGE = className.substring(0 , className.lastIndexOf(".")+1);
    }

    public static void v(Object... contents) {
        log(HiLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(HiLogType.V, tag, contents);
    }


    public static void d(Object... contents) {
        log(HiLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(HiLogType.D, tag, contents);
    }
    public static void i(Object... contents) {
        log(HiLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(HiLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(HiLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(HiLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(HiLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(HiLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(HiLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(HiLogType.A, tag, contents);
    }


    public static void log(@HiLogType.TYPE int type, Object... contents) {
        log(type, HiLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@HiLogType.TYPE int type, String tag, Object... contents) {
        log(HiLogManager.getInstance().getConfig(), type, tag, contents);
    }

    public static void log(HiLogConfig config, @HiLogType.TYPE int type, String tag, Object... contents) {
        //如果false ，则代表 不输出
        if (!config.enable()){
            return;
        }
        StringBuilder sb = new StringBuilder();

        if (config.includeThread()){
            String threadInfo = HiLogConfig.HI_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }

        if (config.stackTraceDepth() > 0 ){
            String stackTrace = HiLogConfig.HI_STACK_TRACE_FORMATTER.format(
                    HiStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace() , HI_LOG_PACKAGE , config.stackTraceDepth())
            );
            sb.append(stackTrace).append("\n");
        }

        //1.我们需要先对日志进行格式化
        String body = parseBody(contents , config);
        sb.append(body);

        List<HiLogPrinter> printers = config.printers()!=null ? Arrays.asList(config.printers()) : HiLogManager.getInstance().getPrinters();

        for (HiLogPrinter printer : printers) {
            printer.print(config , type , tag , sb.toString());
        }
      //  Log.println(type , tag , sb.toString());
    }

    /**
     * 格式化日志
     * @param contents
     * @param config
     * @return
     */
    private static String parseBody(@NonNull Object[] contents , @NonNull HiLogConfig config){
        if (config.injectJsonParser()!=null){
            if (contents.length == 1 && contents[0] instanceof  String){
                return (String) contents[0];
            }
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object content : contents) {
            sb.append(content.toString()).append(";");
        }
        if (sb.length() > 0 ){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
}
