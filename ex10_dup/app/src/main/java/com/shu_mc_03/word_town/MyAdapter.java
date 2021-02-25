package com.shu_mc_03.word_town;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final List<DataModel> dataModelList;
    private final Context mContext;

    public MyAdapter(List<DataModel> modelList, Context context) {
        dataModelList = modelList;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(dataModelList.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView word_textView;
        public TextView exp_textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word_textView = itemView.findViewById(R.id.card_word);
            exp_textView = itemView.findViewById(R.id.card_exp);
        }

        public void bindData(DataModel dataModel, Context context) {
            word_textView.setText(dataModel.getWord());
            exp_textView.setText(dataModel.getExplanation());
        }
    }
}
