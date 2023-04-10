package com.junbao.library.log;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 4:10 下午
 * desc   : 堆栈打印工具类
 */
public class HiStackTraceUtil {

    public static StackTraceElement[] getCroppedRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage, int maxDepth) {
        return cropStackTrace(getRealStackTrack(stackTrace, ignorePackage), maxDepth);
    }

    private static StackTraceElement[] getRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage) {
        int ignoreDepth = 0;
        int allDepth = stackTrace.length;
        String className;
        for (int i = allDepth - 1 ; i >= 0 ; i--){
            //获取堆栈包名
            className = stackTrace[i].getClassName();
            if (ignorePackage !=null && className.startsWith(ignorePackage)){
                ignoreDepth= i+1;
                break;
            }
        }

        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(stackTrace, ignoreDepth, realStack, 0, realDepth);
        return realStack;
    }


    /**
     *
     * @param callStack
     * @param maxDepth
     * @return
     */
    public static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth) {
        //获取堆栈的 长度
        int realDepth = callStack.length;
        if (maxDepth > 0) {
            //获取最小深度
            realDepth = Math.min(maxDepth, realDepth);
        }
        //创建新的堆栈
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        //copy
        System.arraycopy(callStack, 0, realStack, 0, realDepth);
        return realStack;
    }
}
