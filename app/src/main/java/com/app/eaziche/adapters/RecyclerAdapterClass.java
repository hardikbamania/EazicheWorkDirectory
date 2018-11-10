package com.app.eaziche.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.eaziche.R;
import com.app.eaziche.datas.SubCategories;

import java.util.ArrayList;

/**
 * Created by hardik on 26-10-2016.
 */

public class RecyclerAdapterClass extends RecyclerView.Adapter<RecyclerAdapterClass.MyViewHolder> {

    Context context;
    ArrayList<SubCategories> subcategories;

    public RecyclerAdapterClass(Context context) {
        this.context =context ;
        subcategories = new ArrayList<>();
    }

    public void setListSubCategories(ArrayList<SubCategories> subCategories){
        this.subcategories = subCategories;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(subcategories.get(position).getName());
        holder.tvDesc.setText(subcategories.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return subcategories.size();
    }

    static class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvDesc;
         MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
        }
    }
}
