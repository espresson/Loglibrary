package com.junbao.library.log;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 3:53 下午
 * desc   : 堆栈信息解析器
 */
public class HiStackTraceFormatter implements HiLogFormatter<StackTraceElement[]>{
    @Override
    public String format(StackTraceElement[] stackTrace) {
        StringBuilder sb = new StringBuilder(128);
        if (stackTrace == null || stackTrace.length == 0 ){
            return  null;
        }else if (stackTrace.length == 1){
            return "\t- "+stackTrace[0].toString();
        }else {
            //
            for (int i = 0, len = stackTrace.length; i < len ; i++) {
                if (i == 0 ){
                    sb.append("stackTrace: \n");
                }
//                0!=4
//                        1!=4
//                                2!=4
//                                        3!=4

                if (i != len-1){
                    sb.append("\t├ ");
                    sb.append(stackTrace[i].toString());
                    sb.append("\n");
                }else {
                    sb.append("\t└ ");
                    sb.append(stackTrace[i].toString());
                }
            }
        }
        return sb.toString();
    }
}
