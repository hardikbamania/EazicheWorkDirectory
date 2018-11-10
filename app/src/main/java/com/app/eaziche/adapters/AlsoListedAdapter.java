package com.app.eaziche.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eaziche.R;
import com.app.eaziche.fragments.SubCategoryListFragment;
import com.app.eaziche.models.SubCategories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hardik on 26-10-2016.
 */

public class AlsoListedAdapter extends RecyclerView.Adapter<AlsoListedAdapter.MyViewHolder> {

    Context context;
    ArrayList<SubCategories> subcategories;
    static SubCategoryListFragment fragment;

    public AlsoListedAdapter(Context context) {
        this.context = context;
        subcategories = new ArrayList<>();
    }

    public AlsoListedAdapter(Context context, SubCategoryListFragment fragment) {
        this.context =context ;
        subcategories = new ArrayList<>();
        this.fragment = fragment;
    }

    public void setListSubCategories(ArrayList<SubCategories> subCategories){
        this.subcategories = subCategories;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.also_listed_in,parent,false);
        return new MyViewHolder(view,context,subcategories);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(subcategories.get(position).getName());
        Picasso.with(context).load(context.getString(R.string.url)+"sub_category/"+subcategories.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return subcategories.size();
    }

    static class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;
        Context c;
        ArrayList<SubCategories> list;
        public ImageView image;

        MyViewHolder(View itemView, Context context, ArrayList<SubCategories> subcategories) {
            super(itemView);
             c=context;
             list = subcategories;
            tvName = (TextView) itemView.findViewById(R.id.tvAlsoListedInName);
            image = (ImageView) itemView.findViewById(R.id.ivAlsoListedIn);
             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            Toast.makeText(c, ""+i, Toast.LENGTH_SHORT).show();
        }
    }
}
