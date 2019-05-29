package com.example.softomateidentifyerl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by VNPrk on 20.10.2018.
 */

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryViewHolder>{

    private Context context;
    class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvHistoryText;
        TextView tvHistoryLang;

        HistoryViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        void initViews(View itemView) {
            tvHistoryText = (TextView)itemView.findViewById(R.id.tv_history_text);
            tvHistoryLang = (TextView)itemView.findViewById(R.id.tv_history_lang);
        }
    }

    List<TextClass> data;

    public HistoryRecyclerAdapter(Context _context, List<TextClass> data) {
        this.data = data;
        context=_context;
    }

    public void setData(List<TextClass> users){
        data=users;
        notifyDataSetChanged();
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);
        final HistoryViewHolder viewHolder = new HistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int i) {
        holder.tvHistoryText.setText(data.get(i).getText());
		holder.tvHistoryLang.setText(data.get(i).getLang());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}