# AndroidLibrary

#### 介绍
安卓通用组件库

#### 软件架构
java
Kotlin

#### 安装教程

1.  Android Studio
2.  Idea

#### 使用说明
1.全局配置

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

2.具体使用

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

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
