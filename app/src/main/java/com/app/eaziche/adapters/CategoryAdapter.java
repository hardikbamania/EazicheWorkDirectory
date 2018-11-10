package com.app.eaziche.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eaziche.R;
import com.app.eaziche.models.Category;
import com.app.eaziche.fragments.Home;
import com.app.eaziche.fragments.SubCategoryListFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import static com.app.eaziche.activities.MainActivity.FRAME;

/**
 * Created by hardik on 14-10-2016.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoriesViewHolder> {

    private ArrayList<Category> categories = new ArrayList<>();
    private Context context;
    static Home home;


    public CategoryAdapter(ArrayList<Category> categories, Context context, Home home) {
        this.categories = categories;
        this.context = context;
        this.home = home;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_view_layout, parent, false);
        return new CategoriesViewHolder(v, context, categories);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
       // holder.img.setBackgroundDrawable(categories.get(position).getImage());
          holder.img.setImageDrawable(categories.get(position).getImage());
        holder.name.setText(categories.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        TextView name;
        ArrayList<Category> categories = new ArrayList<>();
        Context ctx;
        CategoriesViewHolder(View itemView, Context ctx, ArrayList<Category> list) {
            super(itemView);
            categories = list;
            this.ctx = ctx;
            img = (ImageView) itemView.findViewById(R.id.image_categories);
            name = (TextView) itemView.findViewById(R.id.tvcname);

            itemView.setOnClickListener(this);
        }
        Category category;
        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            category = categories.get(i);
            EventBus.getDefault().postSticky(category);
            home.getFragmentManager().beginTransaction().replace(FRAME, new SubCategoryListFragment(), SubCategoryListFragment.class.getName()).addToBackStack(SubCategoryListFragment.class.getName()).commit();

        }
    }

}
