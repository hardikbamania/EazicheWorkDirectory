package com.app.eaziche.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.eaziche.R;
import com.app.eaziche.fragments.ProfileListFragment;
import com.app.eaziche.fragments.SubCategoryListFragment;
import com.app.eaziche.models.SubCategories;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import static com.app.eaziche.activities.MainActivity.FRAME;

/**
 * Created by hardik on 26-10-2016.
 */

public class SubCategoryAdapterClass extends RecyclerView.Adapter<SubCategoryAdapterClass.MyViewHolder> {

    Context context;
    ArrayList<SubCategories> subcategories;
    static SubCategoryListFragment fragment;

    public SubCategoryAdapterClass(Context context,SubCategoryListFragment fragment) {
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
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list,parent,false);
        return new MyViewHolder(view,context,subcategories);
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

    static class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;
        TextView tvDesc;
        Context c;
        ArrayList<SubCategories> list;
         MyViewHolder(View itemView, Context context, ArrayList<SubCategories> subcategories) {
            super(itemView);
             c=context;
             list = subcategories;
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            SubCategories categories = list.get(i);
            EventBus.getDefault().postSticky(categories);
            fragment.getFragmentManager().beginTransaction().replace(FRAME, new ProfileListFragment(), ProfileListFragment.class.getName()).addToBackStack(ProfileListFragment.class.getName()).commit();

        }
    }
}
