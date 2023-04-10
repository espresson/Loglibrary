package com.junbao.androidlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.junbao.library.log.*

/**
 *    author : Majunbao
 *    github : https://github.com/MaJunBaox
 *    time   : 2022/5/13 1:38 下午
 *    desc   : 
 */
class MainActivity : AppCompatActivity() {


    var viewPrinter : HiViewPrinter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPrinter = HiViewPrinter(this)


        findViewById<View>(R.id.tv_log).setOnClickListener {
            printLog()
        }

        viewPrinter!!.viewPrinterProvider.showFloatingView()


    }

    private fun printLog() {
        HiLogManager.getInstance().addPrinter(viewPrinter)
        HiLog.e("Majunbao Test Log")

        Thread{
            HiLog.e("线程测试")
        }.start()

        HiLog.log(object : HiLogConfig() {
            override fun includeThread(): Boolean {
                return false
            }
        }, HiLogType.E, "---", "5566")
        HiLog.a("9900")
    }
}