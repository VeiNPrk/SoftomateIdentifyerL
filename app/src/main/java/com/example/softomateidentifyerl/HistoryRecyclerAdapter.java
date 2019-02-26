package com.example.softomateidentifyerl;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VNPrk on 20.10.2018.
 */

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryViewHolder>{

    Context context;
    //public static final String MSG_NO_DATA="Адрес неопознан";
    //private SparseBooleanArray selectedItems;
    //private DescribeClickListener describeClickListener;
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

    public HistoryRecyclerAdapter(Context _context, /*DescribeClickListener _noteClickListener, */List<TextClass> data) {
        this.data = data;
        context=_context;
        //describeClickListener=_noteClickListener;
        //selectedItems = new SparseBooleanArray();
    }

    /*public void toggleSelection(int pos) {
        /*if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }*/
       /* clearSelections();
        selectedItems.put(pos, true);
        notifyItemChanged(pos);
    }*/

    /*public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }*/

    public void setData(List<TextClass> users){
        data=users;
        notifyDataSetChanged();
    }
/*
    public void addData(NoteClass newData, int position) {
        data.add(position, newData);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }*/

    /*public List<Integer> getSelectedItems() {
        List<Integer> items =
                new ArrayList<Integer>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }*/
    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);
        //final MyCustomView ivIconNote = (MyCustomView)view.findViewById(R.id.iv_icon_note);
        final HistoryViewHolder viewHolder = new HistoryViewHolder(view);
        /*view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = viewHolder.getAdapterPosition();
                describeClickListener.onNoteClick(position);
            }
        });*/

        /*view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = viewHolder.getAdapterPosition();
                describeClickListener.onNoteLongClick(position);
                return true;
            }
        });*/
        return viewHolder/*new NoteViewHolder(view)*/;
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int i) {
        //final TextClass selectedNote = data.get(i);
        holder.tvHistoryText.setText(data.get(i).getText());
		holder.tvHistoryLang.setText(data.get(i).getLang());
        /*if(data.get(i).getStatus()==0) {
            
            holder.tvDescribeStatus.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        }
        else {
            holder.tvDescribeStatus.setText(context.getString(R.string.rv_item_status_done));
            holder.tvDescribeStatus.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }*/
        //holder.itemView.setActivated(selectedItems.get(i, false));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    /*public interface DescribeClickListener {
        void onNoteClick(int position);
        void onNoteLongClick(int position);
    }*/
}