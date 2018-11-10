package com.app.eaziche.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.eaziche.R;
import com.app.eaziche.models.Images;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.app.eaziche.activities.ProfileActivity.USER_ID;

/**
 * Created by hardik on 17-12-2016.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Images> images;
    public ImageAdapter(Context context){
        this.context = context;
        images = new ArrayList<>();
    }

    public void setImages(ArrayList<Images> images){
        this.images = images;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.content_images, parent, false);
        return new ImageAdapter.MyViewHolder(v,context,images);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("IMAGES:",context.getString(R.string.url)+"user/"+USER_ID+"/"+images.get(position).getImage());
       Picasso.with(context).load(context.getString(R.string.url)+"user/"+USER_ID+"/"+images.get(position).getImage()).into(holder.img);
    }



    @Override
    public int getItemCount() {
        return images.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        ArrayList<Images> images;

        MyViewHolder(View itemView, Context context, ArrayList<Images> images) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.ivMyImages);
            this.images = images;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
