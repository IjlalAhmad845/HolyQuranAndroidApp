package com.example.holyquran.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.holyquran.Activities.IndexActivity;
import com.example.holyquran.R;

import java.util.ArrayList;
import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    Context mContext;
    public static List<String> list=new ArrayList<>();

    public IndexAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.index_list_items,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.indexListTextView.setText(list.get(position));
        holder.index.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView indexListTextView;
        TextView index;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            index=itemView.findViewById(R.id.index);
            indexListTextView=itemView.findViewById(R.id.indexListTextView);

            if(IndexActivity.type.equals("Pages"))
                index.setVisibility(View.GONE);
            else index.setVisibility(View.VISIBLE);
        }
    }
}
