package com.example.holyquran.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.holyquran.Activities.IndexActivity;
import com.example.holyquran.R;

import java.util.ArrayList;
import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder>{

    public interface indexItemOnClick {
        void onClick(int position);
    }

    static public indexItemOnClick indexItemOnClick;

    Context mContext;
    public static List<String> list=new ArrayList<>();

    public IndexAdapter(Context mContext, List<String> list,indexItemOnClick indexItemOnClick) {
        this.mContext = mContext;
        this.list = list;
        IndexAdapter.indexItemOnClick =indexItemOnClick;
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
        CardView listCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            index=itemView.findViewById(R.id.index);
            indexListTextView=itemView.findViewById(R.id.indexListTextView);
            listCardView=itemView.findViewById(R.id.listCardView);

            listCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IndexAdapter.indexItemOnClick.onClick(getAdapterPosition());
                }
            });
            if(IndexActivity.type.equals("Pages"))
                index.setVisibility(View.GONE);
            else index.setVisibility(View.VISIBLE);
        }
    }
}
