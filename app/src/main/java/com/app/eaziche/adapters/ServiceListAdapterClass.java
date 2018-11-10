package com.app.eaziche.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eaziche.R;
import com.app.eaziche.activities.ProfileActivity;
import com.app.eaziche.models.ShopItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hardik on 26-10-2016.
 */

public class ServiceListAdapterClass extends RecyclerView.Adapter<ServiceListAdapterClass.MyViewHolder> {

    Context context;
    ArrayList<ShopItems> shopItems;

    public ServiceListAdapterClass(Context context) {
        this.context = context;

                shopItems = new ArrayList<>();
    }

    public void setShopItems(ArrayList<ShopItems> shopItems){
        this.shopItems = shopItems;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content,parent,false);
        MyViewHolder vh = new MyViewHolder(view,context,shopItems);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(shopItems.get(position).getName());
        holder.tvDesc.setText(shopItems.get(position).getbio());
        holder.tvAddress.setText(shopItems.get(position).getAddress());
        holder.tvRates.setText(shopItems.get(position).getRate());
        holder.tvVisits.setText(shopItems.get(position).getViews());
        holder.tvSub.setText(shopItems.get(position).getfollowers());
        Picasso.with(context).load(context.getString(R.string.url)+"/user/profile/"+shopItems.get(position).getLogo()).resize(100,100).centerCrop().into(holder.logo);

    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName,tvDesc,tvAddress,tvRates,tvVisits,tvSub;
        private Context context;
        ArrayList<ShopItems> list;
        public ImageView logo;

        public MyViewHolder(View itemView,Context context,ArrayList<ShopItems> list) {
            super(itemView);
            this.context = context;
            this.list = list;
            tvName = (TextView) itemView.findViewById(R.id.tvCompanyName);
            tvDesc = (TextView) itemView.findViewById(R.id.tvCompanyDesc);
            tvAddress  = (TextView)itemView.findViewById(R.id.tvCompanyAddress);
            tvRates = (TextView) itemView.findViewById(R.id.tvCompanyRates);
            tvVisits= (TextView) itemView.findViewById(R.id.tvCompanyVisit);
            tvSub = (TextView)itemView.findViewById(R.id.tvCompanySub);
            logo =(ImageView)itemView.findViewById(R.id.civCompanyIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int i = getAdapterPosition();
            Intent intent = new Intent(context.getApplicationContext(), ProfileActivity.class);
            ShopItems shopItems= list.get(i);
            intent.putExtra("shopItems",shopItems);
            context.startActivity(intent);

        }
    }
}
