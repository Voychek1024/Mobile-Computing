package com.shu_mc_03.word_town;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<DataModel> dataModelList;
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

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "ADAPTER DEBUG";
        public TextView word_textView;
        public TextView exp_textView;
        public Button button_star;
        public Button button_del;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word_textView = itemView.findViewById(R.id.card_word);
            exp_textView = itemView.findViewById(R.id.card_exp);
            button_del = itemView.findViewById(R.id.prompt_delete);
            button_star = itemView.findViewById(R.id.prompt_star);
        }

        public void bindData(DataModel dataModel, Context context) {
            word_textView.setText(dataModel.getWord());
            exp_textView.setText(dataModel.getExplanation());
            if (dataModel.getStarred()) {
                Log.i(TAG, "bindData: STAR LOADED");
                word_textView.setTextColor(Color.parseColor("#FF007C"));
            }
            button_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Del
                    try {
                        SharedPreferences pref = itemView.getContext().getSharedPreferences("star_word", Context.MODE_PRIVATE);
                        String look_value = dataModelList.get(getAdapterPosition()).getDb_mode() + "/" + dataModelList.get(getAdapterPosition()).getDb_index();
                        String key_found = find_key(pref, look_value);
                        SharedPreferences.Editor editor = itemView.getContext().getSharedPreferences("star_word", Context.MODE_PRIVATE).edit();
                        editor.remove(key_found);
                        editor.apply();
                    }
                    catch (NullPointerException e) {
                        Log.e(TAG, "NOT FOUND IN STAR");
                    }
                    delete_item(getAdapterPosition());
                }
            });
            button_star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Star
                    star_item(getAdapterPosition());
                }
            });
        }
        String find_key(SharedPreferences sharedPreferences, String value) {
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                if (value.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
            throw new NullPointerException();
        }
        private void delete_item(int pos) {
            Toast.makeText(itemView.getContext(), "DEL", Toast.LENGTH_SHORT).show();

            Random r = new Random();
            int i = r.nextInt();

            SharedPreferences.Editor editor = itemView.getContext().getSharedPreferences("del_word", Context.MODE_PRIVATE).edit();
            String editor_write = Integer.toString(dataModelList.get(pos).getDb_mode())+"/"+Integer.toString(dataModelList.get(pos).getDb_index());
            editor.putString("DELWD"+Integer.toString(i), editor_write);
            editor.apply();

            dataModelList.remove(pos);
            notifyDataSetChanged();
        }
        private void star_item(int pos) {
            Log.d(TAG, "star_item() returned: " + dataModelList.get(pos).getWord() + " " + dataModelList.get(pos).getExplanation());
            dataModelList.get(pos).setStarred();


            Random r = new Random();
            int i = r.nextInt();

            SharedPreferences.Editor editor = itemView.getContext().getSharedPreferences("star_word", Context.MODE_PRIVATE).edit();
            String editor_write = Integer.toString(dataModelList.get(pos).getDb_mode())+"/"+Integer.toString(dataModelList.get(pos).getDb_index());
            editor.putString("STWD"+Integer.toString(i), editor_write);
            editor.apply();
            notifyDataSetChanged();
        }
    }
}
