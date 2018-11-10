package com.app.eaziche.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.eaziche.R;
import com.app.eaziche.fragments.ProfileListFragment;
import com.app.eaziche.fragments.SubCategoryListFragment;
import com.app.eaziche.models.Rates;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.app.eaziche.activities.MainActivity.FRAME;

/**
 * Created by hardik on 26-10-2016.
 */

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.MyViewHolder> {

    Context context;
    ArrayList<Rates> rates;
    public RateAdapter(Context context) {
        this.context =context ;
        rates = new ArrayList<>();
    }

    public void setListRates(ArrayList<Rates> rates){
        this.rates = rates;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_view,parent,false);
        return new MyViewHolder(view,context,rates);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(rates.get(position).getName());
        holder.tvComment.setText(rates.get(position).getComment());
        holder.tvRateDetail.setText((String.valueOf(rates.get(position).getRate())));
        holder.ratingBar.setRating(rates.get(position).getRate());
        Picasso.with(context).load(context.getString(R.string.url)+"/user/profile/"+rates.get(position).getImage()).resize(100,100).centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return rates.size();
    }

    static class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;
        TextView tvComment,tvRateDetail;
        AppCompatRatingBar ratingBar;
        CircleImageView imageView;
        Context c;
        ArrayList<Rates> list;
         MyViewHolder(View itemView, Context context, ArrayList<Rates> Rates) {
            super(itemView);
             c=context;
             list = Rates;
            tvName = (TextView) itemView.findViewById(R.id.tvUserName);
             tvComment = (TextView) itemView.findViewById(R.id.tvComment);
             tvRateDetail = (TextView) itemView.findViewById(R.id.tvRateDetail);
             ratingBar = (AppCompatRatingBar) itemView.findViewById(R.id.ratingBar);
             imageView = (CircleImageView) itemView.findViewById(R.id.ivUserImage);
             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();

        }
    }
}
