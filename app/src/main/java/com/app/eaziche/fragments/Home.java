package com.app.eaziche.fragments;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eaziche.R;
import com.app.eaziche.adapters.CategoryAdapter;
import com.app.eaziche.models.Category;

import java.util.ArrayList;

/**
 * Created by hardik on 14-10-2016.
 */

public class Home extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CategoryAdapter adapter;

    String[] names;
    ArrayList<Category> arraylist = new ArrayList<>();

    Resources res;
    TypedArray images;
    Drawable drawable;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_home,container,false);

        try{
            res = getResources();
        images = res.obtainTypedArray(R.array.img);
        names = getResources().getStringArray(R.array.title);

        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
            if(arraylist.isEmpty()){
                arraylist = getcategory();
            }
            adapter = new CategoryAdapter(arraylist,getContext(),this);
            layoutManager = new GridLayoutManager(getContext(),3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);


        }catch (Exception e){
            Log.e("WTHEFUCK ERROR",e.getLocalizedMessage());
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private ArrayList<Category> getcategory() {
        int i = 0;
        for (String n : names) {
            drawable = images.getDrawable(i);
            int j = i+1;
            arraylist.add(new Category(j, drawable, n));
            i++;
        }
        Log.e("GET CATEGORY","Called");
        return arraylist;
    }
}
