package com.junbao.library.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 1:42 下午
 * desc   : log管理类
 */
public class HiLogManager {

    private static HiLogManager instance;
    private HiLogConfig config;
    //装载打印器的集合
    private List<HiLogPrinter> printers = new ArrayList<>();

    private HiLogManager(HiLogConfig config , HiLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static HiLogManager getInstance(){
        return instance;
    }

    public static void init(@NonNull HiLogConfig config  , HiLogPrinter... printers){
        instance = new HiLogManager(config , printers);
    }

    /**
     * 获取config对象
     * @return
     */
    public HiLogConfig getConfig(){
        return config;
    }

    /**
     * 获取打印器集合
     * @return
     */
    public List<HiLogPrinter> getPrinters(){
        return printers;
    }

    /**
     * 添加打印器
     * @param printer
     */
    public void addPrinter(HiLogPrinter printer){
        printers.add(printer);
    }

    /**
     * 删除打印器
     * @param printer
     */
    public void removePrinter(HiLogPrinter printer){
        if (printers!=null){
            printers.remove(printer);
        }
    }


}
