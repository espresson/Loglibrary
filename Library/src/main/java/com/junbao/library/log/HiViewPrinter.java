package com.junbao.library.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.junbao.library.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : Majunbao
 * github : https://github.com/MaJunBaox
 * time   : 2022/5/13 6:34 下午
 * desc   : view打印器
 */
public class HiViewPrinter implements HiLogPrinter{

    private final LogAdapter adapter;
    private final HiViewPrinterProvider viewPrinterProvider;
    private final RecyclerView recyclerView;

    public HiViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        viewPrinterProvider = new HiViewPrinterProvider(rootView, recyclerView);
    }

    /**
     * 获取
     * @return
     */
    @NonNull
    public HiViewPrinterProvider getViewPrinterProvider() {
        return viewPrinterProvider;
    }

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        adapter.addItem(new HiLogMo(System.currentTimeMillis() , level , tag , printString));
        // 滚动到对应的位置
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    public static class LogAdapter extends RecyclerView.Adapter<LogViewHolder>{

        private final LayoutInflater inflater;
        List<HiLogMo> logs = new ArrayList<>();

        /**
         * 添加新数据
         * @param log
         */
        public void addItem(HiLogMo log){
            logs.add(log);
            notifyItemInserted(logs.size()-1);
        }

        public LogAdapter(LayoutInflater inflater) {
           this.inflater = inflater;
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.hilog_item , parent , false);
            return new LogViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            HiLogMo logItem = logs.get(position);

            int color = getHighlightColor(logItem.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);

            holder.tagView.setText(logItem.getFlattened());
            holder.messageView.setText(logItem.log);
        }

        @Override
        public int getItemCount() {
            return logs==null ? 0 : logs.size();
        }

        /**
         * 跟进log级别获取不同的高了颜色
         *
         * @param logLevel log 级别
         * @return 高亮的颜色
         */
        private int getHighlightColor(int logLevel) {
            int highlight;
            switch (logLevel) {
                case HiLogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case HiLogType.D:
                    highlight = 0xffffffff;
                    break;
                case HiLogType.I:
                    highlight = 0xff6a8759;
                    break;
                case HiLogType.W:
                    highlight = 0xffbbb529;
                    break;
                case HiLogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{

        TextView tagView;
        TextView messageView;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tag);
            messageView = itemView.findViewById(R.id.message);
        }
    }
}
