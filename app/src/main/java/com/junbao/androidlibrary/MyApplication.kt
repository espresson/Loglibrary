package com.junbao.androidlibrary

import android.app.Application
import com.google.gson.Gson
import com.junbao.library.log.HiConsolePrinter
import com.junbao.library.log.HiLogConfig
import com.junbao.library.log.HiLogManager

/**
 *    author : Majunbao
 *    github : https://github.com/MaJunBaox
 *    time   : 2022/5/13 2:04 下午
 *    desc   :
 */
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        HiLogManager.init(object : HiLogConfig() {
            override fun getGlobalTag(): String {
                return "liuyi"
            }

            override fun enable(): Boolean {
                return true
            }

            override fun includeThread(): Boolean {
                return true
            }

            override fun injectJsonParser(): JsonParser {
                return JsonParser {
                    src -> Gson().toJson(src)
                }
            }
        } ,HiConsolePrinter())
    }
}