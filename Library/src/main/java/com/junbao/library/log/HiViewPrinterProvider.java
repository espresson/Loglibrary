package com.junbao.library.log;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 6:41 下午
 * desc   : logcat悬浮实现
 */







public class HiViewPrinterProvider {
    private FrameLayout rootView;
    private RecyclerView recyclerView;
    private View floatingView;
    private boolean isOpen;
    private FrameLayout logView;

    public HiViewPrinterProvider(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }

    private static final String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";
    private static final String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    /**
     * 展示悬浮
     */
    public void showFloatingView(){
        if (rootView.findViewWithTag(TAG_FLOATING_VIEW) != null){
            return;
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM | Gravity.END;
        View floatingView = getFloatingView();
        floatingView.setTag(TAG_FLOATING_VIEW);
        floatingView.setBackgroundColor(Color.BLACK);
        floatingView.setAlpha(0.8f);
        params.bottomMargin = HiDisplayUtil.dp2px(100, rootView.getResources());
        rootView.addView(floatingView , params);
    }

    /**
     * 绘制悬浮
     * @return
     */
    private View getFloatingView() {
        if (floatingView !=null){
            return floatingView;
        }
        TextView textView = new TextView(rootView.getContext());
        textView.setOnClickListener(view -> {
            if (!isOpen){
                showLogView();
            }
        });

        textView.setText("HiLog");
        return floatingView = textView;
    }

    /**
     * 展示logCat
     */
    private void showLogView() {
        if (rootView.findViewWithTag(TAG_LOG_VIEW) !=null){
            return;
        }

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HiDisplayUtil.dp2px(160, rootView.getResources()));
        params.gravity = Gravity.BOTTOM;
        View logView = genLogView();
        logView.setTag(TAG_LOG_VIEW);
        rootView.addView(genLogView() , params);
        isOpen = true;
    }

    /**
     * 绘制loacat
     * @return
     */
    private View genLogView() {
        if (logView!=null){
            return logView;
        }
        FrameLayout logView = new FrameLayout(rootView.getContext());
        logView.setBackgroundColor(Color.BLACK);
        logView.addView(recyclerView);


        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.END;
        TextView closeView = new TextView(rootView.getContext());
        closeView.setOnClickListener(v ->{
            closeLogView();
        });
        closeView.setText("Close");
        logView.addView(closeView , params);
        return this.logView = logView;
    }

    /**
     * 关闭logcat窗口
     */
    private void closeLogView() {
        isOpen = false;
        rootView.removeView(genLogView());
    }

}
