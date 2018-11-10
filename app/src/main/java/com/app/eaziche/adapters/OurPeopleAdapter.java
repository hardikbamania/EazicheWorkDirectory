package com.app.eaziche.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eaziche.R;
import com.app.eaziche.fragments.Home;
import com.app.eaziche.fragments.SubCategoryListFragment;
import com.app.eaziche.models.Category;
import com.app.eaziche.models.OurPeople;
import com.app.eaziche.models.SubCategories;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import static com.app.eaziche.activities.MainActivity.FRAME;

/**
 * Created by hardik on 14-10-2016.
 */

public class OurPeopleAdapter extends RecyclerView.Adapter<OurPeopleAdapter.ourPeopleViewHolder> {

    private ArrayList<OurPeople> ourPeople;
    private Context context;


    public OurPeopleAdapter(Context context) {
        ourPeople = new ArrayList<>();
        this.context = context;
    }

    public void setListOurPeople(ArrayList<OurPeople> ourPeople){
        this.ourPeople = ourPeople;
        notifyDataSetChanged();
    }

    @Override
    public ourPeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.our_people_layout, parent, false);
        return new ourPeopleViewHolder(v, context, ourPeople);
    }

    @Override
    public void onBindViewHolder(ourPeopleViewHolder holder, int position) {

        holder.name.setText(ourPeople.get(position).getName());
        Picasso.with(context).load(context.getString(R.string.url)+"user/profile/"+ourPeople.get(position).getImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return ourPeople.size();
    }

    class ourPeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        TextView name;
        Button button;
        ArrayList<OurPeople> ourPeople = new ArrayList<>();
        OurPeople people;
        Context ctx;

        ourPeopleViewHolder(View itemView, Context ctx, ArrayList<OurPeople> list) {
            super(itemView);
            ourPeople = list;
            this.ctx = ctx;
            img = (ImageView) itemView.findViewById(R.id.image_profile);
            name = (TextView) itemView.findViewById(R.id.tvcname);
            button = (Button) itemView.findViewById(R.id.bFollowButton);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            Toast.makeText(ctx, ourPeople.get(i).getName(), Toast.LENGTH_SHORT).show();
        }
    }

}
